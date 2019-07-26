package com.example.githubtraining.dagger.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationContextModule(val app: Application) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return app
    }

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return app
    }
}