package my.repository.marvelapp.helper

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.GsonBuilder

object PreferenceHelper {
    private const val PREFERENCE_KEY = "marvel_preference"

    fun write(context: Context, key: String, value: String) {
        val preference = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)

        val editor = preference.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun write(context: Context, key: String, data: Any) {
        val preference = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)

        val gson = GsonBuilder().create()
        val listString = gson.toJson(data)
        preference.edit().putString(key, listString).apply()
    }

    fun read(context: Context, key: String): Any? {
        val preference = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
        return preference.all[key]
    }

    fun clear(context: Context) {
         val preference = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
        preference.edit().clear().apply()

        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply()
    }

    fun clear(context: Context, key: String) {
        val preference = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
        preference.edit().remove(key).apply()
    }

    fun sameOfCache(context: Context, key: String, data: Any): Boolean {
         val preference = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)

        val gson = GsonBuilder().create()
        val fromServer = gson.toJson(data)
        val fromCache = preference.getString(key, null)
        return fromServer == fromCache
    }
}