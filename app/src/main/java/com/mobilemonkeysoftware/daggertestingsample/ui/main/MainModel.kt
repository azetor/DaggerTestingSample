package com.mobilemonkeysoftware.daggertestingsample.ui.main

import com.mobilemonkeysoftware.api.GithubApi
import hu.akarnokd.rxjava2.math.MathObservable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by AR on 10/03/2018.
 */
class MainModel @Inject constructor(
        private val githubApi: GithubApi
) {

    fun getLastGist() = MathObservable
            .max(
                    githubApi.getPublicGistOk().flatMap { Observable.fromIterable(it) },
                    Comparator { e0, e1 -> e0.createdAt.compareTo(e1.createdAt) }
            )

}