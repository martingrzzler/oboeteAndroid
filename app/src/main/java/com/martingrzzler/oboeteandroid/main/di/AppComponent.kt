package com.martingrzzler.oboeteandroid.main.di

import android.app.Application
import com.martingrzzler.oboeteandroid.main.BaseApplication
import com.martingrzzler.oboeteandroid.main.di.main.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        SubComponentsModule::class
    ]
)
interface AppComponent  {


    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun mainComponent(): MainComponent.Factory

}