package com.example.githubtraining.dagger.component

import com.example.githubtraining.dagger.module.*
import com.example.githubtraining.screen.infoUser.InfoUserActivity
import com.example.githubtraining.screen.infoUser.InfoUserViewModel
import com.example.githubtraining.screen.login.LoginActivity
import com.example.githubtraining.screen.login.LoginRepository
import com.example.githubtraining.screen.repoDetails.RepoDetailsActivity
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
@Component(modules = [ViewModelFactoryModule::class,ViewModelModule::class,ApplicationContextModule::class, SharedPreferencesModule::class,AppDataBaseModule::class, RetrofitModule::class ])
interface AppComponent {


    fun inject(application: InfoUserActivity)
    fun inject(application: LoginActivity)
    fun inject(application: SplashScreenActivity)
    fun inject(application: RepositoriesActivity)
    fun inject(application: RepoDetailsActivity)
    fun inject(application: LoginRepository)


}