package my.repository.marvelapp.characterList

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.characters_list_activity.*
import my.repository.marvelapp.R
import my.repository.marvelapp.characterDetail.CharacterDetailActivity
import my.repository.marvelapp.characterList.adapter.CharacterAdapter
import my.repository.marvelapp.extensions.RxExtensions.ioThread
import my.repository.marvelapp.helper.CharacterHelper
import my.repository.marvelapp.model.Character
import java.io.IOException
import java.net.SocketTimeoutException

class CharacterListActivity : AppCompatActivity() {

    private val adapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }

    private val viewModel: CharacterViewModel by lazy {
        ViewModelProviders.of(this).get(CharacterViewModel::class.java)
    }

    private val viewModelDesc: CharacterDescViewModel by lazy {
        ViewModelProviders.of(this).get(CharacterDescViewModel::class.java)
    }

    companion object {
        fun starter(activity: Activity) {
            val intent = Intent(activity, CharacterListActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.characters_list_activity)
        val window = this@CharacterListActivity.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.red)

        recycler_view_characters.adapter = adapter
        recycler_view_characters.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter.callback = {
            openCharacteretails(it)
        }
        subscribeOnList()

        filter.setOnClickListener { sortingItems() }

        swipe.setOnRefreshListener { subscribeOnList() }

    }

    private fun sortingItems() {

        viewModelDesc.characterDescList
            .ioThread()
            .subscribe({
                it?.let {list->
                    adapter.submitList(list)
                    swipe.isRefreshing = false
                    progress_loading.visibility = View.GONE

                }
            }, { throwable ->
                when (throwable) {
                    is SocketTimeoutException -> {
                        onConnectionError()
                    }
                    is IOException -> {
                        onConnectionError()
                    }
                }
                Log.e(CharacterListActivity::class.java.simpleName, throwable.message, throwable)
            }).addTo(CompositeDisposable())

    }

    private fun onConnectionError() {
        CharacterHelper.errorDialog(
            this@CharacterListActivity,
            getString(R.string.error_title_conn),
            getString(R.string.error_conn_msg)
        )
        progress_loading.visibility = View.GONE
        swipe.isRefreshing = false
    }

    private fun onErrorSuccessLoad() {
        CharacterHelper.errorDialog(
            this@CharacterListActivity,
            getString(R.string.error_char_title),
            getString(R.string.error_char_not_avaiable)
        )
        progress_loading.visibility = View.GONE
        swipe.isRefreshing = false
    }

    private fun subscribeOnList() {
        viewModel.characterList
            .ioThread()
            .subscribe({
                it?.let { list ->
                    adapter.submitList(list)
                    swipe.isRefreshing = false
                    progress_loading.visibility = View.GONE
                }
            }, { throwable ->
                when (throwable) {
                    is SocketTimeoutException -> {
                        onConnectionError()
                    }
                    is IOException -> {
                        onConnectionError()
                    }
                }
                Log.e(CharacterListActivity::class.java.simpleName, throwable.message, throwable)
            }).addTo(CompositeDisposable())
    }

    private fun openCharacteretails(it: Character.Results) {
        CharacterDetailActivity.starter(this@CharacterListActivity, it)
    }

}