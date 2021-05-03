package com.ulanapp.skywebprotestapp.data.source

import com.ulanapp.skywebprotestapp.domain.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    companion object {
        private const val WEATHER = "data/2.5/weather"
    }

    // метод для получения данных о погоде
    @GET(WEATHER)
    fun getWeatherData(
        @Query("id") id: Int,
        @Query("appid") appid: String,
        @Query("lang") lang: String,
        @Query("units") units: String
    ): Single<WeatherResponse>
}