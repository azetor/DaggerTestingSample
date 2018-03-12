package com.mobilemonkeysoftware.ui

import android.content.Context
import com.mobilemonkeysoftware.api.GithubApi
import dagger.Module
import dagger.Provides

/**
 * Created by AR on 12/03/2018.
 */
@Module
class UiContractModule {

    @Provides
    fun provideMainModel(githubApi: GithubApi): MainModel = MainModel(githubApi)

    @Provides
    fun provideMainPresenter(model: MainModel, context: Context): MainPresenter = MainPresenter(model, context)

}