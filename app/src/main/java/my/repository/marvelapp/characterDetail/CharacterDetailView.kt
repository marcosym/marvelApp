package my.repository.marvelapp.characterDetail

import my.repository.marvelapp.model.Character
import my.repository.marvelapp.model.Comic

interface CharacterDetailView {
    fun configureOnClickListeners()
    fun loadCharacterDetails(charModel: Character.Results)
    fun onReceivedComics(results: MutableList<Comic.Results>)
    fun onReceivedSeries(results: MutableList<Comic.Results>)
    fun onReceivedStories(results: MutableList<Comic.Results>)
    fun onReceivedEvents(results: MutableList<Comic.Results>)
}