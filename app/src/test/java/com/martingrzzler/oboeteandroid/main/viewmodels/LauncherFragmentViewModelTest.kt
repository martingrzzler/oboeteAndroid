package com.martingrzzler.oboeteandroid.main.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.martingrzzler.oboeteandroid.main.data.FakeDataSource
import com.martingrzzler.oboeteandroid.main.repositories.WordRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LauncherFragmentViewModelTest{

    private lateinit var launcherFragmentViewModel: LauncherFragmentViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUpViewModel() {
        launcherFragmentViewModel = LauncherFragmentViewModel(WordRepository(FakeDataSource()))
    }

    @Test
    fun viewModel_is_not_null() {
        assertNotNull(launcherFragmentViewModel)
    }


}