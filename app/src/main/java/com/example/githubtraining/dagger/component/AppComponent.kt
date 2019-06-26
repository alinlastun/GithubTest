package com.example.githubtraining.dagger.component

import com.example.githubtraining.dagger.module.AppDataBaseModule
import com.example.githubtraining.dagger.module.RepoModule
import com.example.githubtraining.dagger.module.SharedPreferencesModule
import com.example.githubtraining.screen.infoUser.InfoUserActivity
import com.example.githubtraining.screen.login.LoginActivity
import com.example.githubtraining.screen.repoDetails.RepoDetailsRepository
import com.example.githubtraining.screen.repositories.RepositoriesActivity
import com.example.githubtraining.screen.splashScreen.SplashScreenActivity
import com.example.githubtraining.utill.repository.RepositoryRepoDB
import com.example.githubtraining.utill.repository.RepositoryStuffDB
import com.example.githubtraining.utill.repository.RepositoryUserDB
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPreferencesModule::class,AppDataBaseModule::class, RepoModule::class])
interface AppComponent {

    /**
     * Created injects for SharedPreference in all classes where you use it
     */
    fun inject(application: InfoUserActivity)
    fun inject(application: LoginActivity)
    fun inject(application: SplashScreenActivity)
    fun inject(application: RepositoriesActivity)

    /**
     * Created injects for DataBase in all classes where you use it
     */
    fun injectDatabase(application: RepositoryRepoDB)
    fun injectDatabase(application: RepositoryStuffDB)
    fun injectDatabase(application: RepositoryUserDB)


    fun getRepositoryRepoDB(): RepositoryRepoDB



}