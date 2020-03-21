package com.martingrzzler.oboeteandroid.main.ui.main


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.martingrzzler.oboeteandroid.R
import com.martingrzzler.oboeteandroid.main.BaseApplication
import com.martingrzzler.oboeteandroid.main.di.main.MainComponent

class MainActivity : AppCompatActivity() {

    lateinit var mainComponent: MainComponent



    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (application as BaseApplication).appComponent.mainComponent().create()
        mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
