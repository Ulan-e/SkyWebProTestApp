package com.ulanapp.skywebprotestapp.domain.repository

import com.ulanapp.skywebprotestapp.domain.model.WeatherResponse
import io.reactivex.Single

interface WeatherRepository {

    fun getWeatherData(cityId: Int): Single<WeatherResponse>
}