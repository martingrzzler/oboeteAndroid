package com.martingrzzler.oboeteandroid.main.di


import com.martingrzzler.oboeteandroid.main.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule{


    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}