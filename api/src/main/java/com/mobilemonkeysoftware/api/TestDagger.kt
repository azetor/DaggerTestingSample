package com.mobilemonkeysoftware.api

import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by AR on 11/03/2018.
 */
internal class TestApp {

    @Inject
    lateinit var githubApi: GithubApi
}

@Singleton
@Component(
        modules = [
            ApiModule::class
        ]
)
internal interface TestComponent {

    fun inject(app: TestApp)
}

