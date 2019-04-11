//package my.repository.marvelapp.extensions
//
//import android.content.Context
//import android.util.Log
//import android.view.View
//import com.google.gson.GsonBuilder
//import io.reactivex.Completable
//import io.reactivex.Maybe
//import io.reactivex.Observable
//import io.reactivex.Single
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.disposables.Disposable
//import io.reactivex.schedulers.Schedulers
//import my.repository.marvelapp.helper.PreferenceHelper
//import java.lang.reflect.Type
//import java.util.concurrent.TimeUnit
//
//fun clearCache(context: Context, key: String){
//    PreferenceHelper.clear(context, key)
//}
//
//fun <T> Observable<T>.useCache(key: String, type: Type): Observable<T> {
//    return Observable.create<T> { emitter ->
//        try {
//           val cache = loadPersistedCache<T>(key, type)
//            if(cache != null){
//                if(!emitter.isDisposed) {
//                    emitter.onNext(cache)
//                }
//            }
//
//            try {
//                val result = this.blockingFirst()
//                if (result?.hashCode() != cache?.hashCode()) {
//                    if(!emitter.isDisposed) {
//                        emitter.onNext(result)
//                    }
//                    createSaveCache(key, result)
//                }
//
//            }catch (throwable: Throwable){
//                Log.e("Cache", throwable.message, throwable)
//            }
//        } catch (throwable: Throwable) {
//            if(!emitter.isDisposed){
//                emitter.onError(throwable)
//            }
//        }
//
//        emitter.onComplete()
//    }
//}
//
//fun <T> Observable<T>.useMemoryCache(context: Context, key: String, type: Type): Observable<T> {
//    return Observable.create<T> { emitter ->
//        try {
//            var cache = loadMemoryCache<T>(context, key, type)
//
//            if (cache != null) {
//                if(!emitter.isDisposed) {
//                    emitter.onNext(cache)
//                }
//            } else {
//                cache = loadPersistedCache<T>(context, key, type)
//                if(cache != null){
//                    if(!emitter.isDisposed) {
//                        emitter.onNext(cache)
//                    }
//                }
//
//                try {
//                    val result = this.blockingFirst()
//                    if (result?.hashCode() != cache?.hashCode()) {
//                        if(!emitter.isDisposed) {
//                            emitter.onNext(result)
//                        }
//                        createSaveCache(key, result)
//                    }
//
//                }catch (throwable: Throwable){
//                    Log.e("Cache", throwable.message, throwable)
//                }
//            }
//        } catch (throwable: Throwable) {
//            if(!emitter.isDisposed){
//                emitter.onError(throwable)
//            }
//        }
//
//        emitter.onComplete()
//    }
//}
//
//private fun <T> loadMemoryCache(context: Context, key: String, type: Type): T? {
//    return PreferenceHelper.read(context, key, type)
//}
//
//private fun <T> loadPersistedCache(context: Context, key: String, type: Type): T? {
//    val json = PreferenceHelper.read(context, key) as? String
//    json?.apply {
//        try {
//            val gson = GsonBuilder().create()
//            val value = gson.fromJson<T>(this, type)
//
//            return value
//        } catch (t: Throwable) {
//            Log.w("LoadFromCache", t)
//        }
//    }
//
//    return null
//}
//
//private fun <T> createSaveCache(key: String, value: T) {
//    val gson = GsonBuilder().create()
//    val listString = gson.toJson(value)
//    PreferenceHelper.write(key, listString)
//}
//
