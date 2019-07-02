package com.example.githubtraining.dagger.module

import com.example.githubtraining.retrofit.ServiceUtil
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ServiceUtilModule {

    private val BASE_URL ="https://api.github.com"

    @Provides
    @Singleton
    internal fun getServiceUtil(retrofit: Retrofit): ServiceUtil {
        return retrofit.create<ServiceUtil>(ServiceUtil::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(client:OkHttpClient, gson:GsonConverterFactory,rxJavaAdapterFactory: RxJava2CallAdapterFactory):Retrofit{
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(gson)
            .addCallAdapterFactory(rxJavaAdapterFactory)
            .baseUrl(BASE_URL)
            .build()
    }


}