package com.ulanapp.skywebprotestapp.di.modules

import com.ulanapp.skywebprotestapp.presentation.login.LoginFragment
import com.ulanapp.skywebprotestapp.presentation.images.ImagesFragment
import com.ulanapp.skywebprotestapp.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector()
    abstract fun mainActivity(): MainActivity
}

@Module
abstract class FragmentBuilderModule {

    @WeatherScope
    @ContributesAndroidInjector(modules = [WeatherModule::class])
    abstract fun loginFragment(): LoginFragment

    @ImagesScope
    @ContributesAndroidInjector(modules = [ImagesModule::class])
    abstract fun imagesFragment(): ImagesFragment
}