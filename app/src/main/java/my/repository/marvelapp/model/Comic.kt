package my.repository.marvelapp.model

import com.google.gson.annotations.SerializedName

class Comic {

    @SerializedName("offset")
    var offset: Int? = null
    @SerializedName("limit")
    var limit: Int? = null
    @SerializedName("total")
    var total: Int? = null
    @SerializedName("count")
    var count: Int? = null
    @SerializedName("results")
    var results: MutableList<Results>? = arrayListOf()

    class Results {
        @SerializedName("thumbnail")
        var thumbnail: Thumbnail? = null
        @SerializedName("title")
        var title: String? = null
    }

    class Thumbnail {
        var path: String? = null
        var extension: String? = null
    }
}