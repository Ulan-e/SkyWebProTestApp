package com.ulanapp.skywebprotestapp.di.component

import com.ulanapp.skywebprotestapp.presentation.base.BaseApplication
import com.ulanapp.skywebprotestapp.di.modules.ActivityBuilderModule
import com.ulanapp.skywebprotestapp.di.modules.AppModule
import com.ulanapp.skywebprotestapp.di.modules.FragmentBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: BaseApplication): Builder
        fun build(): AppComponent
    }
}