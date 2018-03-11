package com.mobilemonkeysoftware.daggertestingsample.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.mobilemonkeysoftware.daggertestingsample.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by AR on 10/03/2018.
 */
class MainPresenter @Inject constructor(
        private val model: MainModel,
        private val context: Context
) {

    private lateinit var view: MainView

    private var disposable: Disposable? = null

    fun init(view: MainView) {
        this.view = view
    }

    private fun Gist.formatted() = "LAST GIST:" +
            "\nid=${this.id}," +
            "\ndescription=${this.description}," +
            "\ncomments=${this.comments}," +
            "\nurl=${this.url}," +
            "\nforksUrl=${this.forksUrl}," +
            "\ncommitsUrl=${this.commitsUrl}," +
            "\ngitPullUrl=${this.gitPullUrl}," +
            "\ngitPushUrl=${this.gitPushUrl}," +
            "\nhtmlUrl=${this.htmlUrl}," +
            "\ncommentsUrl=${this.commentsUrl}," +
            "\ncreatedAt=${this.createdAt}," +
            "\nupdatedAt=${this.updatedAt}," +
            "\npublic=${this.public}," +
            "\ntruncated=${this.truncated}"

    @SuppressLint("LogNotTimber")
    fun start() {

        disposable = model
                .getLastGist()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { view.show(it.formatted()) },
                        onComplete = { Toast.makeText(context, R.string.loaded, Toast.LENGTH_LONG).show() },
                        onError = { Log.e("MAIN_PRESENTER", "Get last gits error", it) })
    }

    fun stop() {
        disposable?.dispose()
    }

}