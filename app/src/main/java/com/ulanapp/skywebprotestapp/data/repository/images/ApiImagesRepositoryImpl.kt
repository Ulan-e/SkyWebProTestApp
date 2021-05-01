package com.ulanapp.skywebprotestapp.data.repository.images

import com.ulanapp.skywebprotestapp.data.source.ImagesApiService
import com.ulanapp.skywebprotestapp.domain.model.ImagesResponse
import io.reactivex.Single

class ApiImagesRepositoryImpl(
    private val imagesApi: ImagesApiService
) : ApiImagesRepository {

    override fun getImagesList(page: Int, limit: Int): Single<ImagesResponse> {
        return imagesApi.getImagesList(page, limit)
    }
}