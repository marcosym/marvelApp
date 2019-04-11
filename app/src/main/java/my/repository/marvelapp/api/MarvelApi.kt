package my.repository.marvelapp.api

import io.reactivex.Observable
import my.repository.marvelapp.extensions.RxExtensions
import my.repository.marvelapp.helper.CharacterHelper
import my.repository.marvelapp.model.CharacterResponse
import my.repository.marvelapp.model.ComicResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private const val PRIVATE_KEY = "128b06d73bfe26041fbaf2c776d868a0e26c4a75"
private const val PUBLIC_KEY = "5bb58cf740634499feec2a17233eab61"

interface MarvelApi {

    @GET("characters")
    fun loadChars(@Query("offset") offset: Int?): Observable<CharacterResponse>

    @GET("characters")
    fun loadCharsOrderByModifiedAsc(@Query("orderBy") orderBy: String?, @Query("offset") offset: Int?): Observable<CharacterResponse>

    @GET("characters")
    fun loadCharsOrderByModifiedDsc(@Query("orderBy") orderBy: String?, @Query("offset") offset: Int?): Observable<CharacterResponse>

    @GET("characters/{characterId}/comics")
    fun loadComics(@Path("characterId") characterId: Int, @Query("offset") offset: Int?): Observable<ComicResponse>

    @GET("characters/{characterId}/series")
    fun loadSeries(@Path("characterId") characterId: Int): Observable<ComicResponse>

    @GET("characters/{characterId}/stories")
    fun loadStories(@Path("characterId") characterId: Int): Observable<ComicResponse>

    @GET("characters/{characterId}/events")
    fun loadEvents(@Path("characterId") characterId: Int): Observable<ComicResponse>

    companion object {
        fun getService(): MarvelApi {
            val authOkHttpClient: OkHttpClient.Builder? = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor {
                    val orignal = it.request()
                    val originalUrl = orignal.url()

                    val timeStamp = CharacterHelper.getTimeStamp()
                    val url = originalUrl.newBuilder()
                        .addQueryParameter("apikey", PUBLIC_KEY)
                        .addQueryParameter("ts", timeStamp.toString())
                        .addQueryParameter("hash", RxExtensions.md5("$timeStamp$PRIVATE_KEY$PUBLIC_KEY"))
                        .build()

                    it.proceed(orignal.newBuilder().url(url).build())
                }


            val retrofit = Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com/v1/public/")
                .client(authOkHttpClient?.build()!!)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create<MarvelApi>(MarvelApi::class.java)

        }
    }

}