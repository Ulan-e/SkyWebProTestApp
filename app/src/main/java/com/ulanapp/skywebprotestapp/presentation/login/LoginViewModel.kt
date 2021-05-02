package com.ulanapp.skywebprotestapp.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ulanapp.skywebprotestapp.domain.model.WeatherResponse
import com.ulanapp.skywebprotestapp.domain.usecase.GetWeatherUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private var disposable: CompositeDisposable = CompositeDisposable()

    val loadingProgress = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val data = MutableLiveData<WeatherResponse>()

    // загружаем сведений о погоде
    fun getWeatherInfo(cityId: Int, apiKey: String, lang: String, units: String) {
        loadingProgress.value = true

        disposable.add(
            getWeatherUseCase.execute(cityId, apiKey, lang, units)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { result ->
                        loadingProgress.value = false
                        data.value = result
                    },
                    { error ->
                        loadingProgress.value = false
                        errorMessage.value = error.message
                    })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}