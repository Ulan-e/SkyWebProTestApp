package com.ulanapp.skywebprotestapp.domain.repository

import com.ulanapp.skywebprotestapp.domain.model.ImagesResponse
import io.reactivex.Single

interface ImagesRepository {

    fun getImagesList(page: Int, limit: Int): Single<List<ImagesResponse>>
}