package com.martingrzzler.oboeteandroid.main.network


import com.martingrzzler.oboeteandroid.main.model.DataResponse
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{

    @GET("search/{query}")
    fun getWord(@Path("query")query: String) : Call<DataResponse>

}