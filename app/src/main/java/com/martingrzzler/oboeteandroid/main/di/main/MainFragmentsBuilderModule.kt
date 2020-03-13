package com.martingrzzler.oboeteandroid.main.di.main

import com.martingrzzler.oboeteandroid.main.ui.main.LauncherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentsBuilderModule {

    @ContributesAndroidInjector()
    abstract fun contributeLauncherFragment(): LauncherFragment
}