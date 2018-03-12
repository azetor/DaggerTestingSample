package com.mobilemonkeysoftware.ui

import com.nhaarman.mockito_kotlin.doThrow
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import it.cosenonjaviste.daggermock.InjectFromComponent
import org.junit.*


/**
 * Created by AR on 12/03/2018.
 */
class MainPresenterTest {

    private companion object Util {

        fun verifyFormattedGist(formattedGist: String?): Boolean = formattedGist != null
                && formattedGist.contains("id=", false)
                && formattedGist.contains("description=", false)
                && formattedGist.contains("comments=", false)
                && formattedGist.contains("user=", false)
                && formattedGist.contains("url=", false)
                && formattedGist.contains("forksUrl=", false)
                && formattedGist.contains("commitsUrl=", false)
                && formattedGist.contains("gitPullUrl=", false)
                && formattedGist.contains("gitPushUrl=", false)
                && formattedGist.contains("htmlUrl=", false)
                && formattedGist.contains("commentsUrl=", false)
                && formattedGist.contains("createdAt=", false)
                && formattedGist.contains("updatedAt=", false)
                && formattedGist.contains("public=", false)
                && formattedGist.contains("truncated=", false)
    }

    @get:Rule
    internal val rule = daggerMockRule()

    @InjectFromComponent(TestApp::class)
    lateinit var presenter: MainPresenter

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun clean() {
    }

    @Test
    fun testStart() {

        var result: String? = null
        val mainView: MainView = object : MainView {
            override fun show(formattedGist: String) {
                result = formattedGist
            }
        }

        presenter.init(mainView)
        presenter.start()

        Assert.assertNotNull(result)
        Assert.assertNotEquals("", result)
    }

    @Test
    fun testStop() {

        var result: String? = null
        val mainView: MainView = object : MainView {
            override fun show(formattedGist: String) {
                result = formattedGist
            }
        }

        presenter.init(mainView)
        presenter.stop()

        Assert.assertNull(result)
    }

    @Test
    fun testVerifyGist() {

        var result: String? = null
        val mainView: MainView = object : MainView {
            override fun show(formattedGist: String) {
                result = formattedGist
            }
        }

        presenter.init(mainView)
        presenter.start()

        Assert.assertNotNull(result)
        Assert.assertTrue(verifyFormattedGist(result))
    }

    @Test(expected = RuntimeException::class)
    fun testInitStartStop() {

        val presenterMock: MainPresenter = mock()

        doThrow(RuntimeException("Init")).`when`(presenterMock).init(mock())
        doThrow(RuntimeException("Start")).`when`(presenterMock).start()
        doThrow(RuntimeException("Stop")).`when`(presenterMock).stop()

        presenterMock.init(mock())
        presenterMock.start()
        presenterMock.stop()

        verify(presenterMock).start()
        verify(presenterMock).stop()
    }

}