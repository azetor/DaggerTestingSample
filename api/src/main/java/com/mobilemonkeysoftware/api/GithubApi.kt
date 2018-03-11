package com.mobilemonkeysoftware.api

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import retrofit2.http.GET
import java.io.Serializable
import java.util.*

/**
 * Created by AR on 10/03/2018.
 */
interface GithubApi {

    @GET("/gists/public")
    fun getPublicGistOk(): Observable<List<Gist>>

    @GET("/gists/public")
    fun getPublicGistError(): Observable<Gist>

    data class Gist(
            val id: String,
            val description: String,
            val comments: String,
            val user: String?,
            val url: String,
            @SerializedName("forks_url") val forksUrl: String,
            @SerializedName("commits_url") val commitsUrl: String,
            @SerializedName("git_pull_url") val gitPullUrl: String,
            @SerializedName("git_push_url") val gitPushUrl: String,
            @SerializedName("html_url") val htmlUrl: String,
            @SerializedName("comments_url") val commentsUrl: String,
            @SerializedName("created_at") val createdAt: Date,
            @SerializedName("updated_at") val updatedAt: Date,
            val public: Boolean,
            val truncated: Boolean
    ) : Serializable

}
