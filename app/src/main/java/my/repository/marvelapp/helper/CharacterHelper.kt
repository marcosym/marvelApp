package my.repository.marvelapp.helper

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import java.util.*

private const val PREFERENCE_KEY = "marvel_preference"

object CharacterHelper {

    fun errorDialog(activity: Activity, title: String, msg: String) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun write(context: Context, key: String, value: String) {
        val preference = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)

        val editor = preference.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun read(context: Context, key: String): Any? {
        val preference = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
        return preference.all[key]
    }

    fun clear(context: Context, key: String) {
        val preference = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
        preference.edit().remove(key).apply()
    }

    fun getTimeStamp(): Long{
        return (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis/1000)
    }

}