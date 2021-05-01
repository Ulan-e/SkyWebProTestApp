package com.ulanapp.skywebprotestapp.data.repository.weather

import com.ulanapp.skywebprotestapp.data.source.WeatherApiService
import com.ulanapp.skywebprotestapp.domain.model.WeatherResponse
import com.ulanapp.skywebprotestapp.domain.repository.WeatherRepository
import io.reactivex.Single

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApiService
) : WeatherRepository {

    override fun getWeatherData(cityId: Int): Single<WeatherResponse> {
        return weatherApi.getWeatherData("c35880b49ff95391b3a6d0edd0c722eb")
    }
}