package my.repository.marvelapp.characterDetail

import io.reactivex.Observable
import my.repository.marvelapp.model.Comic
import my.repository.marvelapp.model.ComicResponse

interface CharacterInteractor {

    companion object {
        val instance: CharacterInteractor by lazy { CharacterInteractorImpl() }
    }

     fun loadComics(characterId: Int): Observable<Comic>
     fun loadSeries(characterId: Int): Observable<Comic>
     fun loadStories(characterId: Int): Observable<Comic>
     fun loadEvents(characterId: Int): Observable<Comic>

}