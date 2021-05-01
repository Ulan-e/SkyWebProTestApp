package com.ulanapp.skywebprotestapp.domain.usecase

import com.ulanapp.skywebprotestapp.domain.model.WeatherResponse
import com.ulanapp.skywebprotestapp.domain.repository.WeatherRepository
import io.reactivex.Single
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepo: WeatherRepository
) {

    fun execute(cityId: Int): Single<WeatherResponse> {
        return weatherRepo.getWeatherData(cityId)
    }
}