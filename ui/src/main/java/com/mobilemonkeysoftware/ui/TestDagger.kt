package com.mobilemonkeysoftware.ui

import android.app.Activity
import android.app.Application
import android.content.Context
import com.mobilemonkeysoftware.api.ApiModule
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by AR on 12/03/2018.
 */
internal class TestApp : Application() {

    @Inject
    lateinit var presenter: MainPresenter
}

@Module
internal class TestModule {

    @Singleton
    @Provides
    fun provideContext(): Context = Activity()
}

@Singleton
@Component(
        modules = [
            TestModule::class,
            ApiModule::class,
            UiContractModule::class
        ]
)
internal interface TestComponent {

    fun inject(app: TestApp)
}

