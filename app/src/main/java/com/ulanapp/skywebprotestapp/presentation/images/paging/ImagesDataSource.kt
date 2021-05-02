package com.ulanapp.skywebprotestapp.presentation.images.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.ulanapp.skywebprotestapp.domain.model.ImagesResponse
import com.ulanapp.skywebprotestapp.domain.usecase.GetImagesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ImagesDataSource(
    private val compositeDisposable: CompositeDisposable,
    private val getImagesUseCase: GetImagesUseCase
) : PageKeyedDataSource<Int, ImagesResponse>() {

    companion object {
        const val FIRST_PAGE = 1
    }

    val state = MutableLiveData<State>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ImagesResponse>
    ) {
        updateState(State.LOADING)

        compositeDisposable.add(
            getImagesUseCase.execute(FIRST_PAGE, params.requestedLoadSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response ->
                        updateState(State.SUCCESS)
                        callback.onResult(response, null, FIRST_PAGE + 1)
                        Timber.d(response.toString())
                    },
                    { error ->
                        updateState(State.ERROR)
                        Timber.e(error.localizedMessage)
                    })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ImagesResponse>) {}

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ImagesResponse>
    ) {
        updateState(State.LOADING)

        compositeDisposable.add(
            getImagesUseCase.execute(params.key, params.requestedLoadSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response ->
                        updateState(State.SUCCESS)
                        callback.onResult(response, params.key + 1)
                        Timber.d(response.toString())
                    },
                    { error ->
                        updateState(State.ERROR)
                        Timber.e(error.localizedMessage)
                    })
        )
    }

    // обновляем состояние загрузки данных
    private fun updateState(state: State) {
        this.state.postValue(state)
    }
}