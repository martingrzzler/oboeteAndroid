package com.martingrzzler.oboeteandroid.main.di.main

import androidx.lifecycle.ViewModel
import com.martingrzzler.oboeteandroid.main.di.ViewModelKey
import com.martingrzzler.oboeteandroid.main.viewmodels.LauncherFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MainViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(LauncherFragmentViewModel::class)
    abstract fun bindLauncherFragmentViewModel(launcherFragmentViewModel: LauncherFragmentViewModel) : ViewModel
}