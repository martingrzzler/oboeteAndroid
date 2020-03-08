package com.martingrzzler.oboeteandroid.main.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.martingrzzler.oboeteandroid.main.repositories.WordRepository
import java.io.IOException


class LauncherFragmentViewModel : ViewModel() {
    private val repo: WordRepository = WordRepository()


    fun makeQueryCallUserInput(query: String) = liveData {
        try {
            val result = repo.makeQueryCall(query)
            emit(result.data)
        } catch (e: IOException) {
            Log.i("LauncherFragmentVM", "There was an Error with the connection: ${e.message}")
        }

    }

}