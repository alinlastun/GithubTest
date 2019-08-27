package com.example.githubtraining.mvi

import androidx.lifecycle.ViewModelProvider

interface HasViewModelFactory {
    var viewModelFactory: ViewModelProvider.Factory
}