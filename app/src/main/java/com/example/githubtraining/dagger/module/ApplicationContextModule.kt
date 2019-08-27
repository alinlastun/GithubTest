package com.example.githubtraining.dagger.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationContextModule {

    @Singleton
    @Provides
    fun provideApplicationContext(app: Application): Context {
        return app
    }
}