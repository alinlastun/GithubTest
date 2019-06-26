package com.example.githubtraining.utill

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.githubtraining.screen.infoUser.InfoUserViewModel
import com.example.githubtraining.screen.login.LoginViewModel
import com.example.githubtraining.screen.repoDetails.RepoDetailsViewModel
import com.example.githubtraining.screen.repositories.RepositoriesViewModel
import com.example.githubtraining.screen.settings.SettingsViewModel

class LocalViewModelFactory(private val nContext: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(nContext) as T
        }else if (modelClass.isAssignableFrom(InfoUserViewModel::class.java)) {
            return InfoUserViewModel(nContext) as T
        }else if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(nContext) as T
        }else if (modelClass.isAssignableFrom(RepositoriesViewModel::class.java)) {
            return RepositoriesViewModel(nContext) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}