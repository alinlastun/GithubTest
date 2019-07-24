package com.example.githubtraining.dagger.module

import com.example.githubtraining.retrofit.ServiceUtil
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ServiceUtilModule (val BASE_URL : String) {


    @Provides
    @Singleton
    internal fun getServiceUtil(retrofit: Retrofit): ServiceUtil {
        return retrofit.create(ServiceUtil::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(client:OkHttpClient, gson:GsonConverterFactory,coroutineCallAdapterFactory: CoroutineCallAdapterFactory):Retrofit{
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(gson)
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .baseUrl(BASE_URL)
            .build()
    }


}