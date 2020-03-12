package com.martingrzzler.oboeteandroid.main.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.martingrzzler.oboeteandroid.main.repositories.WordRepository
import java.io.IOException

enum class ResponseState{LOADING, ERROR, DONE}

class LauncherFragmentViewModel : ViewModel() {
    private val repo: WordRepository = WordRepository()



    private val _apiStatus = MutableLiveData<ResponseState>()
    val apiStatus: LiveData<ResponseState> get() = _apiStatus


    fun makeQueryCallUserInput(query: String) = liveData {
        try {
            _apiStatus.value = ResponseState.LOADING
            val result = repo.makeQueryCall(query)
            _apiStatus.value = ResponseState.DONE
            emit(result.data)
        } catch (e: IOException) {
            _apiStatus.value = ResponseState.ERROR
            Log.i("LauncherFragmentVM", "There was an Error with the connection: ${e.message}")
            _apiStatus.value = ResponseState.DONE
        }

    }


}