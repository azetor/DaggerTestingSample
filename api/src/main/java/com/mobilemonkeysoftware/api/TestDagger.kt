package com.mobilemonkeysoftware.api

import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by AR on 11/03/2018.
 */
class TestApp {

    @Inject
    lateinit var githubApi: GithubApi
}

@Singleton
@Component(
        modules = [
            ApiModule::class
        ]
)
interface TestComponent {

    fun inject(app: TestApp)
}

