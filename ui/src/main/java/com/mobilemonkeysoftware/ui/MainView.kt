package com.mobilemonkeysoftware.ui

import com.mobilemonkeysoftware.api.GithubApi

typealias Gist = GithubApi.Gist

/**
 * Created by AR on 10/03/2018.
 */
interface MainView {

    fun show(formattedGist: String)

}