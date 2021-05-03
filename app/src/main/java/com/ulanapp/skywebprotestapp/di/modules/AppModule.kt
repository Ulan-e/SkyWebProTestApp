package com.ulanapp.skywebprotestapp.di.modules

import android.content.Context
import com.ulanapp.skywebprotestapp.presentation.base.BaseApplication
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun context(application: BaseApplication): Context
}