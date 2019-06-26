package com.example.githubtraining.dagger.component

import com.example.githubtraining.MainApplication
import com.example.githubtraining.dagger.module.SharedPreferencesModule
import com.example.githubtraining.screen.infoUser.InfoUserActivity
import com.example.githubtraining.screen.login.LoginActivity
import com.example.githubtraining.screen.repositories.RepositoriesActivity
import com.example.githubtraining.screen.splashScreen.SplashScreenActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPreferencesModule::class])
interface AppComponent {

    fun inject(application: InfoUserActivity)
    fun inject(application: LoginActivity)
    fun inject(application: SplashScreenActivity)
    fun inject(application: RepositoriesActivity)

}