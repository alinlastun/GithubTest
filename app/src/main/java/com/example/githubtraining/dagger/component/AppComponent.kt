package com.example.githubtraining.dagger.component
import com.example.githubtraining.MainActivity
import com.example.githubtraining.dagger.module.AppDataBaseModule
import com.example.githubtraining.dagger.module.AppExecutorModule
import com.example.githubtraining.dagger.module.ApplicationContextModule
import com.example.githubtraining.dagger.module.ServiceUtilModule
import com.example.githubtraining.dagger.module.SharedPreferencesModule
import com.example.githubtraining.dagger.module.ViewModelModule
import com.example.githubtraining.ui.infoUser.InfoUserFragment
import com.example.githubtraining.ui.login.LoginFragment
import com.example.githubtraining.ui.login.LoginRepository
import com.example.githubtraining.ui.repoDetails.RepoDetailsFragment
import com.example.githubtraining.ui.repoDetails.RepoDetailsViewModel
import com.example.githubtraining.ui.repositories.RepositoriesFragment
import com.example.githubtraining.ui.settings.FragmentSettings
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, ApplicationContextModule::class, SharedPreferencesModule::class,
    AppDataBaseModule::class, ServiceUtilModule::class, AppExecutorModule::class ])
interface AppComponent {
    fun inject(application: InfoUserFragment)
    fun inject(application: LoginFragment)
    fun inject(application: MainActivity)
    fun inject(application: RepositoriesFragment)
    fun inject(application: RepoDetailsFragment)
    fun inject(application: LoginRepository)
    fun inject(application: FragmentSettings)
    fun inject(application: RepoDetailsViewModel)
}