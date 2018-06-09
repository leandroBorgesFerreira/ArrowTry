package br.com.leandro.arrowtry.retrofit

import android.app.Application
import arrow.syntax.function.pipe
import br.com.leandro.arrowtry.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://api.github.com/"

private val logInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private var retrofitOption : Retrofit? = null

private fun provideHttpCache(application: Application): Cache {
    val cacheSize = 10 * 1024 * 1024
    return Cache(application.cacheDir, cacheSize.toLong())
}

private fun httpClientBuilder(): OkHttpClient.Builder =
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                this.addInterceptor(logInterceptor)
            }
        }

private fun getRetrofitBuilderDefaults() =
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

private fun provideOkHttpClientOAuth(cache: Cache) : OkHttpClient = httpClientBuilder().cache(cache).build()

private fun createRetrofit(application: Application) : Retrofit =
        provideHttpCache(application) pipe { cache ->
            provideOkHttpClientOAuth(cache)
        } pipe { httpClient ->
            getRetrofitBuilderDefaults().client(httpClient).build()
        }

fun initRetrofit(application: Application) {
    if (retrofitOption == null) {
        retrofitOption = createRetrofit(application)
    }
}

fun retrofit() : Retrofit = retrofitOption ?: throw Exception("You need to initialize retrofit before using it!")
