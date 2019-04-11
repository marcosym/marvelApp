package my.repository.marvelapp.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import my.repository.marvelapp.api.MarvelApi
import my.repository.marvelapp.model.Character
import java.util.*

class CharacterDescDataSource(
    private val marvelApi: MarvelApi,
    private val disposable: CompositeDisposable
) : PageKeyedDataSource<Int, Character.Results>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Character.Results>) {
        val numberOfItems = params.requestedLoadSize
        createObservable(0, 1, numberOfItems, callback, null)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character.Results>) {
        val page = params.key
        val numberOfItems = params.requestedLoadSize
        createObservable(page, page + 1, numberOfItems, null, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character.Results>) {
        val page = params.key
        val numberOfItems = params.requestedLoadSize
        createObservable(page, page - 1, numberOfItems, null, callback)
    }

    private fun createObservable(
        requestedPage: Int, adjacentPage: Int, requestedLoadSieze: Int,
        initialCallback: LoadInitialCallback<Int, Character.Results>?,
        callback: LoadCallback<Int, Character.Results>?) {
        marvelApi.loadCharsOrderByModifiedDsc("-modified",requestedPage * requestedLoadSieze)
            .subscribe({
                initialCallback?.onResult(it.data?.results!!, null, adjacentPage)
                callback?.onResult(it.data?.results!!, adjacentPage)
            }, {
                Log.e(CharacterDataSource::class.java.simpleName, it.message, it)
            }).addTo(disposable)
    }


}

