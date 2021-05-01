package com.ulanapp.skywebprotestapp.presentation.base

import com.chibatching.kotpref.Kotpref
import com.ulanapp.skywebprotestapp.BuildConfig
import com.ulanapp.skywebprotestapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}