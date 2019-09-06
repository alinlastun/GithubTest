package com.example.githubtraining.dagger.module
import com.example.githubtraining.BuildConfig
import com.example.githubtraining.api.ApiService
import com.example.githubtraining.utilities.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {


    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(loggingInterceptor)
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.nonstrict.asConverterFactory(MediaType.get("application/json")))
            .client(httpClient.build())
            .build()
    }

    @Singleton
    @Provides
    internal fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

}