package com.ulanapp.skywebprotestapp.data.repository

import com.ulanapp.skywebprotestapp.data.source.WeatherApiService
import com.ulanapp.skywebprotestapp.domain.model.WeatherResponse
import com.ulanapp.skywebprotestapp.domain.repository.WeatherRepository
import io.reactivex.Single

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApiService
) : WeatherRepository {

    override fun getWeatherData(
        cityId: Int,
        apiKey: String,
        lang: String,
        units: String
    ): Single<WeatherResponse> {
        return weatherApi.getWeatherData(cityId, apiKey, lang, units)
    }
}