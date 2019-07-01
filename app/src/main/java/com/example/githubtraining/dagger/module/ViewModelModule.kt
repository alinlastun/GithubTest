package com.example.githubtraining.dagger.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.githubtraining.screen.infoUser.InfoUserViewModel
import com.example.githubtraining.screen.login.LoginViewModel
import com.example.githubtraining.screen.repoDetails.RepoDetailsViewModel
import com.example.githubtraining.screen.repositories.RepositoriesViewModel
import com.example.githubtraining.screen.settings.SettingsViewModel
import com.example.githubtraining.utill.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(InfoUserViewModel::class)
    abstract fun bindInfoUserViewModel(viewModel: InfoUserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoDetailsViewModel::class)
    abstract fun bindRepoDetailsViewModel(viewModel: RepoDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    abstract fun bindRepositoriesViewModel(viewModel: RepositoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}