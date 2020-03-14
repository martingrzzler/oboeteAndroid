package com.martingrzzler.oboeteandroid.main.network


import com.martingrzzler.oboeteandroid.main.di.scopes.MainScope
import com.martingrzzler.oboeteandroid.main.model.DataResponse


import retrofit2.http.GET
import retrofit2.http.Path

@MainScope
interface ApiService{



    @GET("search/{query}")
    suspend fun getWord(@Path("query")query: String): DataResponse

}