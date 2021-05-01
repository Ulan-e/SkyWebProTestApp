package com.ulanapp.skywebprotestapp.data.repository.weather

import com.ulanapp.skywebprotestapp.domain.model.WeatherResponse
import io.reactivex.Single

interface ApiWeatherRepository {

    fun getWeatherData(cityId: Int): Single<WeatherResponse>
}