package com.example.githubtraining.dagger.component
import com.example.githubtraining.dagger.module.AppDataBaseModule
import com.example.githubtraining.dagger.module.AppExecutorModule
import com.example.githubtraining.dagger.module.ApplicationContextModule
import com.example.githubtraining.dagger.module.ServiceUtilModule
import com.example.githubtraining.dagger.module.SharedPreferencesModule
import com.example.githubtraining.dagger.module.ViewModelModule
import com.example.githubtraining.ui.infoUser.InfoUserActivity
import com.example.githubtraining.ui.login.LoginActivity
import com.example.githubtraining.ui.login.LoginRepository
import com.example.githubtraining.ui.repoDetails.RepoDetailsActivity
import com.example.githubtraining.ui.repoDetails.RepoDetailsViewModel
import com.example.githubtraining.ui.repositories.RepositoriesActivity
import com.example.githubtraining.ui.settings.SettingsActivity
import com.example.githubtraining.ui.splashScreen.SplashScreenActivity
import dagger.Component
import javax.inject.Singleton
@Singleton
@Component(modules = [ViewModelModule::class, ApplicationContextModule::class, SharedPreferencesModule::class,
    AppDataBaseModule::class, ServiceUtilModule::class, AppExecutorModule::class ])
interface AppComponent {
    fun inject(application: InfoUserActivity)
    fun inject(application: LoginActivity)
    fun inject(application: SplashScreenActivity)
    fun inject(application: RepositoriesActivity)
    fun inject(application: RepoDetailsActivity)
    fun inject(application: LoginRepository)
    fun inject(application: SettingsActivity)
    fun inject(application: RepoDetailsViewModel)
}