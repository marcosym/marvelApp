package my.repository.marvelapp.characterDetail

import io.reactivex.Observable
import my.repository.marvelapp.api.MarvelApi
import my.repository.marvelapp.model.Comic
import my.repository.marvelapp.model.ComicResponse

class CharacterRepository {

    private val service = MarvelApi.getService()

    fun loadComics(characterId: Int): Observable<Comic> {
        return service.loadComics(characterId = characterId, offset = 1).map { it.data }
    }

    fun loadStories(characterId: Int): Observable<Comic> {
        return service.loadStories(characterId = characterId).map { it.data }

    }

    fun loadSeries(characterId: Int): Observable<Comic> {
        return service.loadSeries(characterId = characterId).map { it.data }
    }

    fun loadEvents(characterId: Int): Observable<Comic> {
        return service.loadEvents(characterId = characterId).map { it.data }

    }
}