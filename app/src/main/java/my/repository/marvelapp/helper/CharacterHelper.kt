package my.repository.marvelapp.helper

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import java.util.*


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

    fun getTimeStamp(): Long{
        return (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis/1000)
    }

}