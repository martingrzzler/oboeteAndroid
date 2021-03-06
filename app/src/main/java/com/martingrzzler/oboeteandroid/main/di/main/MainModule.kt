package com.martingrzzler.oboeteandroid.main.di.main

import com.martingrzzler.oboeteandroid.main.di.scopes.MainScope
import com.martingrzzler.oboeteandroid.main.network.ApiService
import com.martingrzzler.oboeteandroid.main.repositories.WordRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MainModule{

    @JvmStatic
    @MainScope
    @Provides
    fun provideMainApiService(retrofit: Retrofit): ApiService {
       return retrofit.create(ApiService::class.java)
    }

    @JvmStatic
    @MainScope
    @Provides
    fun provideWordRepository(client: ApiService): WordRepository {
        return WordRepository(client)
    }
}
