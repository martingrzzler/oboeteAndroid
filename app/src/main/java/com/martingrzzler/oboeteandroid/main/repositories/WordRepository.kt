package com.martingrzzler.oboeteandroid.main.repositories

import com.martingrzzler.oboeteandroid.main.model.DataResponse
import com.martingrzzler.oboeteandroid.main.network.ApiService
import javax.inject.Inject


class WordRepository @Inject constructor(val client: ApiService){

    suspend fun makeQueryCall(query: String): DataResponse = client.getWord(query)

}