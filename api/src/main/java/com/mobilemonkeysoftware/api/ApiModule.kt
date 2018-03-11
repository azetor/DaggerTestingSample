package com.mobilemonkeysoftware.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by AR on 10/03/2018.
 */
@Module
open class ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .apply {
                retryOnConnectionFailure(true)
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            .build()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
            .serializeNulls()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(client: OkHttpClient, gson: Gson): Retrofit.Builder = Retrofit.Builder()
            .apply {
                client(client)
                addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                addConverterFactory(GsonConverterFactory.create(gson))
            }

    @Provides
    @Singleton
    fun provideGithubApi(builder: Retrofit.Builder): GithubApi = builder
            .baseUrl("https://api.github.com")
            .build()
            .create(GithubApi::class.java)
}