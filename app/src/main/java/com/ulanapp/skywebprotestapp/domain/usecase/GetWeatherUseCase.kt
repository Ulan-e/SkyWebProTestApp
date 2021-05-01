package com.ulanapp.skywebprotestapp.domain.usecase

import com.ulanapp.skywebprotestapp.domain.model.WeatherResponse
import com.ulanapp.skywebprotestapp.domain.repository.WeatherRepository
import io.reactivex.Single

class GetWeatherUseCase constructor(
    private val weatherRepo: WeatherRepository
) {

    fun execute(cityId: Int): Single<WeatherResponse> {
        return weatherRepo.getWeatherData(cityId)
    }
}