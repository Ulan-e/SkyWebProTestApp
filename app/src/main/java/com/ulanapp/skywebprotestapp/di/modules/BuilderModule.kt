package com.ulanapp.skywebprotestapp.di.modules

import com.ulanapp.skywebprotestapp.presentation.AuthorizationFragment
import com.ulanapp.skywebprotestapp.presentation.ImagesFragment
import com.ulanapp.skywebprotestapp.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector()
    abstract fun mainActivity(): MainActivity
}

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [ImagesModule::class])
    abstract fun authorizationFragment(): AuthorizationFragment

    @ContributesAndroidInjector(modules = [WeatherModule::class])
    abstract fun imagesFragment(): ImagesFragment
}