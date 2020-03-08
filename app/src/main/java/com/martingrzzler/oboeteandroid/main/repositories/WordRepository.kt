package com.martingrzzler.oboeteandroid.main.repositories

import com.martingrzzler.oboeteandroid.main.network.ApiService
import com.martingrzzler.oboeteandroid.main.network.Retrofit


class WordRepository {


    var client: ApiService = Retrofit.apiService

    suspend fun makeQueryCall(query: String) = client.getWord(query)


}