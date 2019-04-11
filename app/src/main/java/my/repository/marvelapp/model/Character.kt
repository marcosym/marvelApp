package my.repository.marvelapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*


class Character : Serializable {

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

    class Thumbnail : Serializable {
        @SerializedName("path")
        var path: String? = null
        @SerializedName("extension")
        var extension: String? = null
    }

    class Items : Serializable {
        @SerializedName("resourceURI")
        var resourceURI: String? = null
        @SerializedName("name")
        var name: String? = null
    }

    class Series : Serializable {
        @SerializedName("available")
        var available: Int? = null
        @SerializedName("collectionURI")
        var collectionURI: String? = null
        @SerializedName("items")
        var items: List<Items>? = null
        @SerializedName("returned")
        var returned: Int? = null
    }

    class Stories : Serializable {
        @SerializedName("available")
        var available: Int? = null
        @SerializedName("collectionURI")
        var collectionURI: String? = null
        @SerializedName("items")
        var items: List<Items>? = null
        @SerializedName("returned")
        var returned: Int? = null
    }

    class Events : Serializable {
        @SerializedName("available")
        var available: Int? = null
        @SerializedName("collectionURI")
        var collectionURI: String? = null
        @SerializedName("items")
        var items: List<Items>? = null
        @SerializedName("returned")
        var returned: Int? = null
    }

    class Urls : Serializable {
        @SerializedName("type")
        var type: String? = null
        @SerializedName("url")
        var url: String? = null
    }

    class Comics : Serializable {
        @SerializedName("available")
        var available: Int? = null
        @SerializedName("collectionURI")
        var collectionURI: String? = null
        @SerializedName("items")
        var items: List<Items>? = null
        @SerializedName("returned")
        var returned: Int? = null
    }

    class Results : Serializable {
        @SerializedName("id")
        var id: Int? = null
        @SerializedName("name")
        var name: String? = null
        @SerializedName("description")
        var description: String? = null
        @SerializedName("modified")
        var modified: Date? = null
        @SerializedName("thumbnail")
        var thumbnail: Thumbnail? = null
        @SerializedName("resourceURI")
        var resourceURI: String? = null
        @SerializedName("comics")
        var comics: Comics? = null
        @SerializedName("series")
        var series: Series? = null
        @SerializedName("stories")
        var stories: Stories? = null
        @SerializedName("events")
        var events: Events? = null
        @SerializedName("urls")
        var urls: List<Urls>? = null
    }

    companion object {
        val BY_DATE: Comparator<Character.Results> = object : Comparator<Character.Results> {
            override fun compare(o1: Character.Results?, o2: Character.Results?): Int {
                //return (d1.getTime() > d2.getTime() ? -1 : 1);     //descending
//                return if (d1!!.time > d2!!.time) 1 else -1
//                return o1?.modified!!.compareTo(o2?.modified)
                return when {
                    o1?.modified!! < o2?.modified!! -> -1
                    o1.modified!! > o2.modified!! -> 1
                    else -> 0
                }
            }

            val BY_NAME: Comparator<Character.Results> = object : Comparator<Character.Results> {
                override fun compare(o1: Results?, o2: Results?): Int {
//                return o1?.id!!.compareTo(o2?.id!!)
                    return when {
                        o1?.name!! < o2?.name!! -> -1
                        o1.name!! > o2.name!! -> 1
                        else -> 0
                    }
                }
            }
        }
    }
}