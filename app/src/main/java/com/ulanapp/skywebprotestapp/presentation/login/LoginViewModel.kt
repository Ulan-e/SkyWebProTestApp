package com.ulanapp.skywebprotestapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ulanapp.skywebprotestapp.domain.model.WeatherResponse
import com.ulanapp.skywebprotestapp.domain.usecase.GetWeatherUseCase
import com.ulanapp.skywebprotestapp.presentation.images.paging.ImagesDataSource
import com.ulanapp.skywebprotestapp.presentation.images.paging.State
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class LoginViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val state = MutableLiveData<State>()

    val data = MutableLiveData<WeatherResponse>()

    // загружаем сведений о погоде
    fun getWeatherInfo(cityId: Int, apiKey: String, lang: String, units: String) {
        updateState(State.LOADING)

        compositeDisposable.add(
            getWeatherUseCase.execute(cityId, apiKey, lang, units)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response ->
                        updateState(State.SUCCESS)
                        data.value = response
                        Timber.d(response.toString())
                    },
                    { error ->
                        updateState(State.ERROR)
                        Timber.e(error.localizedMessage)
                    })
        )
    }

    // обновляем состояние
    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun getState(): LiveData<State> = Transformations.switchMap(data) {
        state
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}