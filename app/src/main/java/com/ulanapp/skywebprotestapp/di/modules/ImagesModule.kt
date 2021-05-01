package com.ulanapp.skywebprotestapp.di.modules

import com.ulanapp.skywebprotestapp.data.repository.images.ImagesRepositoryImpl
import com.ulanapp.skywebprotestapp.data.source.ImagesApiService
import com.ulanapp.skywebprotestapp.domain.repository.ImagesRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Scope

@Module
class ImagesModule {

    companion object {
        private val BASE_URL = "https://picsum.photos/"
    }

    @Provides
    @ImagesScope
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @ImagesScope
    fun providesOkHttpClient(): OkHttpClient {
        val httpclient = OkHttpClient.Builder()
        httpclient.apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }
        return httpclient.build()
    }

    @Provides
    @ImagesScope
    fun provideService(retrofit: Retrofit): ImagesRepository {
        val imagesRetrofit = retrofit.create(ImagesApiService::class.java)
        return ImagesRepositoryImpl(imagesRetrofit)
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ImagesScope