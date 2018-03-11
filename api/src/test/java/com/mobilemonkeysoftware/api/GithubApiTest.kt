package com.mobilemonkeysoftware.api

import com.google.gson.JsonSyntaxException
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import it.cosenonjaviste.daggermock.InjectFromComponent
import org.junit.After
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

    @After
    fun clean() {
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

    @Test
    fun testNoValues() {

        var apiMock: GithubApi = mock() // PRO TIP
        val emptyListObservable: Observable<List<GithubApi.Gist>> = Observable.empty()

//        doReturn(emptyListObservable).`when`(apiMock).getPublicGistOk()
        whenever(apiMock.getPublicGistOk()).doReturn(emptyListObservable)

        apiMock
                .getPublicGistOk()
                .test()
                .assertNoValues()
                .assertNoErrors()
                .assertSubscribed()

        verify(apiMock).getPublicGistOk()
    }

}