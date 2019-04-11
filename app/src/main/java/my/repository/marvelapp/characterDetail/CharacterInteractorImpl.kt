package my.repository.marvelapp.characterDetail

import io.reactivex.Observable
import my.repository.marvelapp.model.Comic
import my.repository.marvelapp.model.ComicResponse

class CharacterInteractorImpl : CharacterInteractor {

    private val repository = CharacterRepository()

    override fun loadComics(characterId: Int): Observable<Comic> {
        return repository.loadComics(characterId)
    }

    override fun loadSeries(characterId: Int): Observable<Comic> {
        return repository.loadSeries(characterId)
    }

    override fun loadStories(characterId: Int): Observable<Comic> {
        return repository.loadStories(characterId)
    }

    override fun loadEvents(characterId: Int): Observable<Comic> {
        return repository.loadEvents(characterId)
    }



}