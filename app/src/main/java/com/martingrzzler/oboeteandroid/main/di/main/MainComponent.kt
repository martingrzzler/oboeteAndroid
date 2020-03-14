package com.martingrzzler.oboeteandroid.main.di.main

import com.martingrzzler.oboeteandroid.main.di.scopes.MainScope
import com.martingrzzler.oboeteandroid.main.ui.main.LauncherFragment
import com.martingrzzler.oboeteandroid.main.ui.main.MainActivity
import dagger.Subcomponent

@MainScope
@Subcomponent(modules = [MainModule::class, MainViewModelModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): MainComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(launcherFragment: LauncherFragment)

}