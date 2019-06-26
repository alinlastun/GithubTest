package com.example.githubtraining.dagger.component

import com.example.githubtraining.dagger.module.AppDataBaseModule
import com.example.githubtraining.dagger.module.SharedPreferencesModule
import com.example.githubtraining.screen.infoUser.InfoUserActivity
import com.example.githubtraining.screen.login.LoginActivity
import com.example.githubtraining.screen.repositories.RepositoriesActivity
import com.example.githubtraining.screen.splashScreen.SplashScreenActivity
import com.example.githubtraining.utill.repository.RepositoryRepoDB
import com.example.githubtraining.utill.repository.RepositoryStuffDB
import com.example.githubtraining.utill.repository.RepositoryUserDB
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPreferencesModule::class,AppDataBaseModule::class])
interface AppComponent {

    fun inject(application: InfoUserActivity)
    fun inject(application: LoginActivity)
    fun inject(application: SplashScreenActivity)
    fun inject(application: RepositoriesActivity)

    fun inject(application: RepositoryRepoDB)
    fun inject(application: RepositoryStuffDB)
    fun inject(application: RepositoryUserDB)





}