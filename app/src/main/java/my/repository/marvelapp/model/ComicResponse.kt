package my.repository.marvelapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ComicResponse(@SerializedName("data") var data: Comic?) : Serializable