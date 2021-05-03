package com.ulanapp.skywebprotestapp.presentation.images.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ulanapp.skywebprotestapp.domain.model.ImagesResponse
import com.ulanapp.skywebprotestapp.domain.usecase.GetImagesUseCase
import io.reactivex.disposables.CompositeDisposable

class ImagesDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val getImagesUseCase: GetImagesUseCase
) : DataSource.Factory<Int, ImagesResponse>() {

    val imagesDataSource = MutableLiveData<ImagesDataSource>()

    override fun create(): DataSource<Int, ImagesResponse> {
        val dataSource = ImagesDataSource(compositeDisposable, getImagesUseCase)
        imagesDataSource.postValue(dataSource)
        return dataSource
    }
}