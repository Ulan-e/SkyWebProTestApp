package com.ulanapp.skywebprotestapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ulanapp.skywebprotestapp.domain.usecase.GetWeatherUseCase

class LoginModelFactory(
    private var getWeatherUseCase: GetWeatherUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return LoginViewModel(getWeatherUseCase) as T
    }
}