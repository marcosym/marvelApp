package my.repository.marvelapp.paging

import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import my.repository.marvelapp.api.MarvelApi
import my.repository.marvelapp.model.Character

class CharacterDataDescSourceFactory(private val marvelApi: MarvelApi,
                                     private val disposable: CompositeDisposable) : DataSource.Factory<Int, Character.Results>(){

    override fun create(): DataSource<Int, Character.Results> {
        return CharacterDescDataSource(marvelApi, disposable)
    }

}