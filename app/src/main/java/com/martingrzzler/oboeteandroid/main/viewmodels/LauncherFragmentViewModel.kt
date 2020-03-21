package com.martingrzzler.oboeteandroid.main.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.martingrzzler.oboeteandroid.main.di.scopes.MainScope
import com.martingrzzler.oboeteandroid.main.repositories.WordRepository
import java.io.IOException
import javax.inject.Inject

enum class ResponseState{LOADING, ERROR, DONE}

@MainScope
class LauncherFragmentViewModel @Inject constructor(private val repository: WordRepository): ViewModel() {

    private val _apiStatus = MutableLiveData<ResponseState>()
    val apiStatus: LiveData<ResponseState> get() = _apiStatus


    fun makeQueryCallUserInput(query: String) = liveData {
        try {
            _apiStatus.value = ResponseState.LOADING
            val result = repository.makeQueryCall(query)
            _apiStatus.value = ResponseState.DONE
            emit(result.data)
        } catch (e: IOException) {
            _apiStatus.value = ResponseState.ERROR
            Log.i("LauncherFragmentVM", "There was an Error with the connection: ${e.message}")
            _apiStatus.value = ResponseState.DONE
        }

    }


}