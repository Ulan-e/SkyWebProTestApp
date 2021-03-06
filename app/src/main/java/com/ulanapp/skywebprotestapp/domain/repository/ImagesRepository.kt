package com.ulanapp.skywebprotestapp.domain.repository

import com.ulanapp.skywebprotestapp.domain.model.ImagesResponse
import io.reactivex.Single

interface ImagesRepository {

    // получаем все картинки
    fun getImagesList(page: Int, limit: Int): Single<List<ImagesResponse>>
}