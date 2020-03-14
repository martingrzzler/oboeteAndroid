package com.martingrzzler.oboeteandroid.main.di.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martingrzzler.oboeteandroid.main.di.main.keys.MainViewModelKey
import com.martingrzzler.oboeteandroid.main.di.scopes.MainScope
import com.martingrzzler.oboeteandroid.main.viewmodels.LauncherFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MainViewModelModule{


    @MainScope
    @Binds
    abstract fun bindViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory

    @MainScope
    @Binds
    @IntoMap
    @MainViewModelKey(LauncherFragmentViewModel::class)
    abstract fun bindLauncherFragmentViewModel(launcherFragmentViewModel: LauncherFragmentViewModel) : ViewModel
}