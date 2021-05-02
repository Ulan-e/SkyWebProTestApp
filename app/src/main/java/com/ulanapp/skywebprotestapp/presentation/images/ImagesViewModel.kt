package com.ulanapp.skywebprotestapp.presentation.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ulanapp.skywebprotestapp.domain.model.ImagesResponse
import com.ulanapp.skywebprotestapp.domain.usecase.GetImagesUseCase
import com.ulanapp.skywebprotestapp.presentation.images.paging.ImagesDataSource
import com.ulanapp.skywebprotestapp.presentation.images.paging.ImagesDataSourceFactory
import com.ulanapp.skywebprotestapp.presentation.images.paging.State
import io.reactivex.disposables.CompositeDisposable

class ImagesViewModel(
    getImagesUseCase: GetImagesUseCase
): ViewModel() {

    var imagesList: LiveData<PagedList<ImagesResponse>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 2
    private var imagesDataSourceFactory: ImagesDataSourceFactory =
        ImagesDataSourceFactory(compositeDisposable, getImagesUseCase)

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 1)
            .setEnablePlaceholders(false)
            .build()
        imagesList = LivePagedListBuilder(imagesDataSourceFactory, config).build()
    }

    fun getState(): LiveData<State> = Transformations.switchMap(
        imagesDataSourceFactory.imagesDataSource, ImagesDataSource::state)

    fun listIsEmpty(): Boolean {
        return imagesList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}