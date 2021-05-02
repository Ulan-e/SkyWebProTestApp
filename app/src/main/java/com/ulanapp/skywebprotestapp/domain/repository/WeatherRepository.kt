package com.ulanapp.skywebprotestapp.domain.repository

import com.ulanapp.skywebprotestapp.domain.model.WeatherResponse
import io.reactivex.Single

interface WeatherRepository {

    fun getWeatherData(
        cityId: Int,
        apiKey: String,
        lang: String,
        units: String
    ): Single<WeatherResponse>
}