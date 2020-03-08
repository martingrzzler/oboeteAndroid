package com.martingrzzler.oboeteandroid.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.martingrzzler.oboeteandroid.main.model.Word
import com.martingrzzler.oboeteandroid.main.repositories.WordRepository
import kotlinx.coroutines.Dispatchers

class LauncherFragmentViewModel : ViewModel() {


    var repo: WordRepository = WordRepository()


    fun makeQueryCallUserInput(query: String) = liveData {
        val data = repo.makeQueryCall(query)
        emit(data.data)
    }

}