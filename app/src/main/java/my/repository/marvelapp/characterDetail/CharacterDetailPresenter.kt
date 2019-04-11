package my.repository.marvelapp.characterDetail

import java.io.Serializable

interface CharacterDetailPresenter {
    fun onReceiveModel(serializable: Serializable?)
    fun onCreate()
}