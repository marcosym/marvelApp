package my.repository.marvelapp.paging

import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import my.repository.marvelapp.api.MarvelApi
import my.repository.marvelapp.model.Character
import my.repository.marvelapp.model.Comic

class ComicDataSourceFactory(private val marvelApi: MarvelApi,
                             private val disposable: CompositeDisposable) : DataSource.Factory<Int, Comic.Results>(){

    override fun create(): DataSource<Int, Comic.Results> {
        return ComicDataSource(marvelApi, disposable)
    }

}