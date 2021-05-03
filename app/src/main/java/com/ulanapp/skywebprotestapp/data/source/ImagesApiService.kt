package com.ulanapp.skywebprotestapp.data.source

import com.ulanapp.skywebprotestapp.domain.model.ImagesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApiService {

    companion object {
        private const val IMAGES_LIST = "v2/list"
    }

    // метод для получения изображений
    @GET(IMAGES_LIST)
    fun getImagesList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Single<List<ImagesResponse>>
}