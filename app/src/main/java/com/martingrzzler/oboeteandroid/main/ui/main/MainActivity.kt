package com.martingrzzler.oboeteandroid.main.ui.main


import android.os.Bundle
import com.martingrzzler.oboeteandroid.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
