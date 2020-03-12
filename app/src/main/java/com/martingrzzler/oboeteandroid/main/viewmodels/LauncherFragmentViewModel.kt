package com.martingrzzler.oboeteandroid.main.viewmodels

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.RecyclerView
import com.martingrzzler.oboeteandroid.main.repositories.WordRepository
import java.io.IOException
import java.time.Duration

enum class ResponseState{LOADING, ERROR, DONE, NOTFOUND}

class LauncherFragmentViewModel : ViewModel() {
    private val repo: WordRepository = WordRepository()



    private val _apiStatus = MutableLiveData<ResponseState>()
    val apiStatus: LiveData<ResponseState> get() = _apiStatus


    fun makeQueryCallUserInput(query: String) = liveData {
        try {
            _apiStatus.value = ResponseState.LOADING
            val result = repo.makeQueryCall(query)
            _apiStatus.value = ResponseState.DONE
            if(result.data.isEmpty()) {
                _apiStatus.value = ResponseState.NOTFOUND
            }
            emit(result.data)
        } catch (e: IOException) {
            _apiStatus.value = ResponseState.ERROR
            Log.i("LauncherFragmentVM", "There was an Error with the connection: ${e.message}")
            _apiStatus.value = ResponseState.DONE
        }

    }


}