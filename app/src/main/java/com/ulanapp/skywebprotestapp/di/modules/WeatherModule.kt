package com.ulanapp.skywebprotestapp.di.modules

import com.ulanapp.skywebprotestapp.data.repository.weather.WeatherRepositoryImpl
import com.ulanapp.skywebprotestapp.data.source.WeatherApiService
import com.ulanapp.skywebprotestapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Scope

@Module
class WeatherModule {

    companion object {
        private val BASE_URL = "https://api.openweathermap.org/"
    }

    @Provides
    @WeatherScope
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @WeatherScope
    fun providesOkHttpClient(): OkHttpClient {
        val httpclient = OkHttpClient.Builder()
        httpclient.apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            addInterceptor(Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("appid", "c35880b49ff95391b3a6d0edd0c722eb")
                    .build()
                chain.proceed(request)
            })
        }
        return httpclient.build()
    }

    @Provides
    @WeatherScope
    fun provideService(retrofit: Retrofit): WeatherRepository {
        val weatherRetrofit = retrofit.create(WeatherApiService::class.java)
        return WeatherRepositoryImpl(weatherRetrofit)
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class WeatherScope