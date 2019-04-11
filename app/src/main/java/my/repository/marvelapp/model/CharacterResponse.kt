package my.repository.marvelapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterResponse(@SerializedName("data") var data: Character?) : Serializable
