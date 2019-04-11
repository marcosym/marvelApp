package my.repository.marvelapp.extensions

import android.util.Log
import android.view.View
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.lang.StringBuilder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

object RxExtensions {

    fun <T> Observable<T>.ioThread(): Observable<T> {
        return this.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> Maybe<T>.ioThread(): Maybe<T> {
        return this.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun Completable.ioThread(): Completable {
        return this.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> Single<T>.ioThread(): Single<T> {
        return this.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun timer(delay: Long, unit: TimeUnit, listener: () -> Unit): Disposable {
        return Observable.timer(delay, unit)
                .ioThread()
                .subscribe({ listener() }, { t -> Log.e("Timer", t.message, t) })
    }

    fun View.gone() {
        this.visibility = View.GONE
    }

    fun View.invisible() {
        this.visibility = View.INVISIBLE
    }

    fun View.visible() {
        this.visibility = View.VISIBLE
    }

    fun md5(toEncrypt: String): String {
        return try {
            val digest = MessageDigest.getInstance("md5")
            digest.update(toEncrypt.toByteArray())
            val bytes = digest.digest()
            val sb = StringBuilder()
            for (i in bytes.indices) {
                sb.append(String.format("%02X", bytes[i]))
            }
            sb.toString().toLowerCase()
        } catch (exc: Exception) {
            ""
        }
    }

}