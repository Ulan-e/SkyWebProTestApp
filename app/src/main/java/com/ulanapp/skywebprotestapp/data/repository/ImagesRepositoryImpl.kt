package com.ulanapp.skywebprotestapp.data.repository

import com.ulanapp.skywebprotestapp.data.source.ImagesApiService
import com.ulanapp.skywebprotestapp.domain.model.ImagesResponse
import com.ulanapp.skywebprotestapp.domain.repository.ImagesRepository
import io.reactivex.Single

class ImagesRepositoryImpl(
    private val imagesApi: ImagesApiService
) : ImagesRepository {

    override fun getImagesList(page: Int, limit: Int): Single<List<ImagesResponse>> {
        return imagesApi.getImagesList(page, limit)
    }
}