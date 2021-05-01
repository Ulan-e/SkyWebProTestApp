package com.ulanapp.skywebprotestapp.presentation.images

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ulanapp.skywebprotestapp.domain.model.ImagesResponse
import com.ulanapp.skywebprotestapp.domain.usecase.GetImagesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ImagesViewModel(
    private var getImagesUseCase: GetImagesUseCase
): ViewModel() {

    val loadingProgress = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val data = MutableLiveData<List<ImagesResponse>>()

    private var disposable: CompositeDisposable = CompositeDisposable()

    init {
        loadingProgress.value = true
    }

    fun getAllImages(page: Int, limit: Int) {
        disposable.add(
            getImagesUseCase.execute(page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { result ->
                        loadingProgress.value = false
                        data.value = result
                    },
                    { error ->
                        errorMessage.value = error.message
                    })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}