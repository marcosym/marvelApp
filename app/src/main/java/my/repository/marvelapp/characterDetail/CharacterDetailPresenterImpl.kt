package my.repository.marvelapp.characterDetail

import android.util.Log
import my.repository.marvelapp.model.Character
import my.repository.marvelapp.extensions.RxExtensions.ioThread
import java.io.Serializable

class CharacterDetailPresenterImpl(private val view: CharacterDetailView) : CharacterDetailPresenter {

    private var charModel: Character.Results? = null
    private val characterInteractor = CharacterInteractor.instance

    override fun onCreate() {
        view.configureOnClickListeners()
    }

    override fun onReceiveModel(serializable: Serializable?) {
        serializable?.let {
            charModel = it as Character.Results
            view.loadCharacterDetails(charModel!!)
            loadComics(charModel?.id)
            loadSeries(charModel?.id)
            loadStories(charModel?.id)
            loadEvents(charModel?.id)
        }
    }

    private fun loadComics(characterId: Int?) {
        characterId?.let {
            characterInteractor.loadComics(characterId)
                .ioThread()
                .doOnSubscribe {
                    //FAZER O SKELETON
                }
                .subscribe({
                    if (it.results?.size!! > 0) {
                        view.onReceivedComics(it.results!!)
                    }
                }, {
                    Log.e(CharacterDetailPresenterImpl::class.java.simpleName, it.message, it)
                })
        }
    }

    private fun loadSeries(characterId: Int?) {
        characterId?.let {
            characterInteractor.loadSeries(characterId)
                .ioThread()
                .doOnSubscribe {
                    //FAZER O SKELETON
                }
                .subscribe({
                    if (it.results?.size!! > 0) {
                        view.onReceivedSeries(it.results!!)
                    }
                }, {
                    Log.e(CharacterDetailPresenterImpl::class.java.simpleName, it.message, it)
                })
        }
    }

    private fun loadStories(characterId: Int?) {
        characterId?.let {
            characterInteractor.loadStories(characterId)
                .ioThread()
                .doOnSubscribe {
                    //FAZER O SKELETON
                }
                .subscribe({
                    if (it.results?.size!! > 0) {
                        view.onReceivedStories(it.results!!)
                    }
                }, {
                    Log.e(CharacterDetailPresenterImpl::class.java.simpleName, it.message, it)
                })
        }
    }
    private fun loadEvents(characterId: Int?) {
        characterId?.let {
            characterInteractor.loadEvents(characterId)
                .ioThread()
                .doOnSubscribe {
                    //FAZER O SKELETON
                }
                .subscribe({
                    if (it.results?.size!! > 0) {
                        view.onReceivedEvents(it.results!!)
                    }
                }, {
                    Log.e(CharacterDetailPresenterImpl::class.java.simpleName, it.message, it)
                })
        }
    }
}