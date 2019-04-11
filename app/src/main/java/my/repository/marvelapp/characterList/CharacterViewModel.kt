package my.repository.marvelapp.characterList

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import my.repository.marvelapp.api.MarvelApi
import my.repository.marvelapp.model.Character
import my.repository.marvelapp.paging.CharacterDataSourceFactory

class CharacterViewModel : ViewModel() {

    var characterList: Observable<PagedList<Character.Results>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 20
    private val sourceFactory: CharacterDataSourceFactory

    init {
        sourceFactory = CharacterDataSourceFactory(disposable = compositeDisposable, marvelApi = MarvelApi.getService())

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setPrefetchDistance(10)
            .setEnablePlaceholders(false)
            .build()

        characterList = RxPagedListBuilder(sourceFactory, config).setFetchScheduler(Schedulers.io()).buildObservable()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}