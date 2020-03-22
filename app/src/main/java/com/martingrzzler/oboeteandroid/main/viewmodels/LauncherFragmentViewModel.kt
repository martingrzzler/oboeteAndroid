package com.martingrzzler.oboeteandroid.main.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.martingrzzler.oboeteandroid.main.di.scopes.MainScope
import com.martingrzzler.oboeteandroid.main.model.Word
import com.martingrzzler.oboeteandroid.main.repositories.WordRepository
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

enum class ResponseState{LOADING, ERROR, DONE}

@MainScope
class LauncherFragmentViewModel @Inject constructor(private val repository: WordRepository): ViewModel() {

    private val _apiStatus = MutableLiveData<ResponseState>()
    val apiStatus: LiveData<ResponseState> get() = _apiStatus

    private val _words = MutableLiveData<List<Word>>()
    val words: LiveData<List<Word>> get() = _words

    fun makeQueryCallUserInput(query: String) {
        viewModelScope.launch {
            try {
                _apiStatus.value = ResponseState.LOADING
                val result = repository.makeQueryCall(query)
                _apiStatus.value = ResponseState.DONE
                _words.value = result.data
            } catch (e: IOException) {
                _apiStatus.value = ResponseState.ERROR
                Log.i("LauncherFragmentVM", "There was an Error with the connection: ${e.message}")
                _apiStatus.value = ResponseState.DONE
            }
        }


    }





}