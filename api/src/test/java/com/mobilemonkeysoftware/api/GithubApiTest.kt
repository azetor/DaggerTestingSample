package com.mobilemonkeysoftware.api

import com.google.gson.JsonSyntaxException
import it.cosenonjaviste.daggermock.InjectFromComponent
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by AR on 10/03/2018.
 */
class GithubApiTest {

    @get:Rule
    val rule = daggerMockRule()

    @InjectFromComponent(TestApp::class)
    lateinit var api: GithubApi

    @Before
    fun setup() {

    }

    @Test
    fun testNoError() {

        api
                .getPublicGistOk()
                .test()
                .assertNoErrors()
                .assertComplete()
                .assertSubscribed()
    }

    @Test
    fun testError() {

        api
                .getPublicGistError()
                .test()
                .assertError(JsonSyntaxException::class.java)
                .assertSubscribed()
    }
}