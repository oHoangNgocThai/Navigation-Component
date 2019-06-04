package thaihn.com.navigationcomponent.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BaseNetwork {

    private val BASE_URL = "https://api.darksky.net/"
    private val CONNECT_TIMEOUT = 10L
    private val READ_TIMEOUT = 10L
    private val WRITE_TIMEOUT = 10L

    fun providerHttpClient() = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()

    fun providerRetrofit() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(providerHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun providerWeatherApi() = providerRetrofit().create(WeatherApi::class.java)
}
