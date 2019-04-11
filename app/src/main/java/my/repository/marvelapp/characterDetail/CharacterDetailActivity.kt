package my.repository.marvelapp.characterDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.character_details_activity.*
import my.repository.marvelapp.R
import my.repository.marvelapp.characterDetail.adapter.ComicDetailAdapter
import my.repository.marvelapp.characterDetail.adapter.EventsDetailAdapter
import my.repository.marvelapp.characterDetail.adapter.SeriesDetailAdapter
import my.repository.marvelapp.characterDetail.adapter.StoriesDetailAdapter
import my.repository.marvelapp.extensions.RxExtensions.visible
import my.repository.marvelapp.model.Character
import my.repository.marvelapp.model.Comic

class CharacterDetailActivity : AppCompatActivity(), CharacterDetailView {

    private val presenter: CharacterDetailPresenter = CharacterDetailPresenterImpl(this)

    companion object {
        private const val CHARACTER_MODEL = "CHARACTER_MODEL"
        fun starter(context: Context, characterModel: Character.Results) {
            val intent = Intent(context, CharacterDetailActivity::class.java)
            intent.putExtra(CHARACTER_MODEL, characterModel)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_details_activity)

        presenter.onCreate()
        presenter.onReceiveModel(intent?.extras?.getSerializable(CHARACTER_MODEL))
    }

    override fun configureOnClickListeners() {
        toolbar_left_icon.setOnClickListener { onBackPressed() }
    }

    override fun onReceivedComics(results: MutableList<Comic.Results>) {
        val adapter = ComicDetailAdapter(results)
        recycler_view_comics.adapter = adapter
        recycler_view_comics.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }

    override fun loadCharacterDetails(charModel: Character.Results) {
        constraint_root_char.visible()
        Glide.with(this@CharacterDetailActivity)
            .load(charModel.thumbnail?.path + "." + charModel.thumbnail?.extension)
            .transition(withCrossFade())
            .apply(RequestOptions().centerCrop())
            .into(detail_char_image)

        character_title_name.text = charModel.name
        if (!charModel.description.isNullOrEmpty()) {
            character_description.text = charModel.description
        } else {
            character_description.text = getString(R.string.error_description)
        }
    }

    override fun onReceivedSeries(results: MutableList<Comic.Results>) {
        val adapter = SeriesDetailAdapter(results)
        recycler_view_series.adapter = adapter
        recycler_view_series.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }

    override fun onReceivedStories(results: MutableList<Comic.Results>) {
        val adapter = StoriesDetailAdapter(results)
        recycler_view_stories.adapter = adapter
        recycler_view_stories.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun onReceivedEvents(results: MutableList<Comic.Results>) {
        val adapter = EventsDetailAdapter(results)
        recycler_view_events.adapter = adapter
        recycler_view_events.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)    }

}
