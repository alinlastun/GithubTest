package com.example.githubtraining.dagger.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.githubtraining.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class  SharedPreferencesModule() {

    @Provides @Singleton
    fun provideSharedPreferences(context: Context):SharedPreferences{
        return context.getSharedPreferences("my_shared",Context.MODE_PRIVATE)
    }

}