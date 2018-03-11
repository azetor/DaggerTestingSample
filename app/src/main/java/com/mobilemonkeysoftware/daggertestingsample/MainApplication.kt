package com.mobilemonkeysoftware.daggertestingsample

import android.app.Activity
import android.app.Application
import com.mobilemonkeysoftware.daggertestingsample.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by AR on 10/03/2018.
 */
class MainApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
                .application(this)
                .context(this)
                .build()
                .inject(this)
    }

}