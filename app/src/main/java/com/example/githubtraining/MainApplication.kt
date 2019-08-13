package com.example.githubtraining

import android.app.Application
import com.example.githubtraining.dagger.component.AppComponent
import com.example.githubtraining.dagger.component.DaggerAppComponent
import com.example.githubtraining.dagger.module.ApplicationContextModule
import com.example.githubtraining.dagger.module.ServiceUtilModule

lateinit var appComponent: AppComponent
var isInternetConnection = false

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .applicationContextModule(ApplicationContextModule(this))
            .serviceUtilModule(ServiceUtilModule())
            .build()
    }
}