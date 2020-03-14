package com.martingrzzler.oboeteandroid.main


import android.app.Application
import com.martingrzzler.oboeteandroid.main.di.AppComponent
import com.martingrzzler.oboeteandroid.main.di.DaggerAppComponent

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }


    fun initAppComponent(){
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }


}