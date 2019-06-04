package thaihn.com.navigationcomponent.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import thaihn.com.weatherapp.data.model.Response

interface WeatherApi {

    @GET("forecast/{id}/{lat},{long}")
    fun getWeather(
            @Path(value = "id", encoded = true) key: String,
            @Path(value = "lat", encoded = true) lat: Double,
            @Path(value = "long", encoded = true) long: Double
    ): Observable<Response>
}