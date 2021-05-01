package com.ulanapp.skywebprotestapp.data.repository.weather

import com.ulanapp.skywebprotestapp.data.source.WeatherApiService
import com.ulanapp.skywebprotestapp.domain.model.WeatherResponse
import io.reactivex.Single

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApiService
) : WeatherRepository {

    override fun getWeatherData(cityId: Int): Single<WeatherResponse> {
        return weatherApi.getWeatherData(cityId)
    }
}