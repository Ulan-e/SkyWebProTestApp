package com.ulanapp.skywebprotestapp.domain.usecase

import com.ulanapp.skywebprotestapp.domain.model.ImagesResponse
import com.ulanapp.skywebprotestapp.domain.repository.ImagesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val imagesRepo: ImagesRepository
) {

    fun execute(page: Int, limit: Int): Single<List<ImagesResponse>> {
        return imagesRepo.getImagesList(page, limit)
    }
}