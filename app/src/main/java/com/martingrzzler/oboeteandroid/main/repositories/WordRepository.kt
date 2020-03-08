package com.martingrzzler.oboeteandroid.main.repositories

import androidx.lifecycle.liveData
import com.martingrzzler.oboeteandroid.main.model.DataResponse
import com.martingrzzler.oboeteandroid.main.network.ApiService
import com.martingrzzler.oboeteandroid.main.network.Retrofit
import com.martingrzzler.oboeteandroid.util.GenericApiResponse


class WordRepository {


    var client: ApiService = Retrofit.apiService

    suspend fun makeQueryCall(query: String): DataResponse = client.getWord(query)

}