package com.mobilemonkeysoftware.daggertestingsample.di

import com.mobilemonkeysoftware.daggertestingsample.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by AR on 10/03/2018.
 */
@Module
abstract class ActivityBindingModule {

    @Module
    abstract class MainActivityModule

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun bindMainActivity(): MainActivity

}