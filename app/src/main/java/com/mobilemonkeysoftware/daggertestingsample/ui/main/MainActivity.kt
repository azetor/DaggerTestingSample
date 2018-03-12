package com.mobilemonkeysoftware.daggertestingsample.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mobilemonkeysoftware.daggertestingsample.R
import com.mobilemonkeysoftware.ui.MainPresenter
import com.mobilemonkeysoftware.ui.MainView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject
    internal lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)
        presenter.init(this)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        presenter.start()
    }

    override fun onPause() {
        super.onPause()

        presenter.stop()
    }

    override fun show(formattedGist: String) {
        text.text = formattedGist
    }

}
