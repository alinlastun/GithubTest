package com.example.githubtraining.dagger.component

import com.example.githubtraining.dagger.module.AppDataBaseModule
import com.example.githubtraining.dagger.module.ApplicationContextModule
import com.example.githubtraining.dagger.module.RetrofitModule
import com.example.githubtraining.dagger.module.SharedPreferencesModule
import com.example.githubtraining.screen.infoUser.InfoUserActivity
import com.example.githubtraining.screen.infoUser.InfoUserViewModel
import com.example.githubtraining.screen.login.LoginActivity
import com.example.githubtraining.screen.repoDetails.RepoDetailsViewModel
import com.example.githubtraining.screen.repositories.RepositoriesActivity
import com.example.githubtraining.screen.repositories.RepositoriesViewModel
import com.example.githubtraining.screen.splashScreen.SplashScreenActivity
import com.example.githubtraining.utill.repository.RepositoryRepoDB
import com.example.githubtraining.utill.repository.RepositoryStuffDB
import com.example.githubtraining.utill.repository.RepositoryUserDB
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationContextModule::class, SharedPreferencesModule::class,AppDataBaseModule::class, RetrofitModule::class])
interface AppComponent {

    /**
     * Created injects for SharedPreference in all classes where you use it
     */
    fun injectSP(application: InfoUserActivity)
    fun injectSP(application: LoginActivity)
    fun injectSP(application: SplashScreenActivity)
    fun injectSP(application: RepositoriesActivity)


    fun injectRepoDetailsViewModel(viewModel: RepoDetailsViewModel)

    fun injectInfoUserViewModel(viewModel: InfoUserViewModel)
    fun injectRepositoriesViewModel(viewModel: RepositoriesViewModel)








}