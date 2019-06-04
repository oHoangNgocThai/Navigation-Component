package thaihn.com.navigationcomponent.data.repository

import io.reactivex.Observable
import thaihn.com.navigationcomponent.BuildConfig
import thaihn.com.navigationcomponent.data.remote.WeatherApi
import thaihn.com.weatherapp.data.model.Response

class WeatherRepository(
        val weatherApi: WeatherApi
) {

    fun getWeather(latitude: Double,
                   longitude: Double): Observable<Response> = weatherApi.getWeather(
            lat = latitude, long = longitude, key = BuildConfig.API_KEY)
}