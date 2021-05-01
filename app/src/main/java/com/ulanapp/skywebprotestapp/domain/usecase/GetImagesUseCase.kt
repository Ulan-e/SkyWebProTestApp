package com.ulanapp.skywebprotestapp.domain.usecase

import com.ulanapp.skywebprotestapp.domain.model.ImagesResponse
import com.ulanapp.skywebprotestapp.domain.repository.ImagesRepository
import io.reactivex.Single

class GetImagesUseCase constructor(
    private val imagesRepo: ImagesRepository
) {

    fun execute(page: Int, limit: Int): Single<ImagesResponse> {
        return imagesRepo.getImagesList(page, limit)
    }
}