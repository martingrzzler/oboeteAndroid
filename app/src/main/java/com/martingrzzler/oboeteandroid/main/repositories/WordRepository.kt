package com.martingrzzler.oboeteandroid.main.repositories

import com.martingrzzler.oboeteandroid.main.model.DataResponse
import com.martingrzzler.oboeteandroid.main.network.ApiService
import com.martingrzzler.oboeteandroid.main.network.Retrofit


class WordRepository {


   private var client: ApiService = Retrofit.apiService

    suspend fun makeQueryCall(query: String): DataResponse = client.getWord(query)

}