package com.ulanapp.skywebprotestapp.data.repository.images

import com.ulanapp.skywebprotestapp.domain.model.ImagesResponse
import io.reactivex.Single

interface ApiImagesRepository {

    fun getImagesList( page: Int, limit: Int): Single<ImagesResponse>
}