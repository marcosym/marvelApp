package my.repository.marvelapp.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import my.repository.marvelapp.R
import android.os.Handler
import my.repository.marvelapp.characterList.CharacterListActivity


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_activity)

        loadHandler()
    }


    private fun loadHandler() {
        val handle = Handler()
        handle.postDelayed({
            goToMainScreen()
        }, 3000)
    }

    private fun goToMainScreen(){
        CharacterListActivity.starter(this)
        finish()
    }

}