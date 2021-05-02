package com.ulanapp.skywebprotestapp.presentation.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ulanapp.skywebprotestapp.domain.usecase.GetImagesUseCase

class ImagesViewModelFactory (
    private var getImagesUseCase: GetImagesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return ImagesViewModel(getImagesUseCase) as T
    }
}