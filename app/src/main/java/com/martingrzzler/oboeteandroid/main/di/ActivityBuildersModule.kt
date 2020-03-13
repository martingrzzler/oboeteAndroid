package com.martingrzzler.oboeteandroid.main.di

import com.martingrzzler.oboeteandroid.main.di.main.MainFragmentsBuilderModule
import com.martingrzzler.oboeteandroid.main.di.main.MainModule
import com.martingrzzler.oboeteandroid.main.di.main.MainViewModelModule
import com.martingrzzler.oboeteandroid.main.di.scopes.MainScope
import com.martingrzzler.oboeteandroid.main.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuildersModule{

    @MainScope
    @ContributesAndroidInjector(modules = [MainViewModelModule::class, MainFragmentsBuilderModule::class, MainModule::class])
    abstract fun contributeMainActivity() : MainActivity

}