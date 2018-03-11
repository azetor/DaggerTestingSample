package com.mobilemonkeysoftware.daggertestingsample.di

import android.content.Context
import com.mobilemonkeysoftware.api.ApiModule
import com.mobilemonkeysoftware.daggertestingsample.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by AR on 10/03/2018.
 */
@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            ActivityBindingModule::class,
            ApplicationModule::class,
            ApiModule::class
        ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MainApplication): ApplicationComponent.Builder

        @BindsInstance
        fun context(context: Context): ApplicationComponent.Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: MainApplication)

}