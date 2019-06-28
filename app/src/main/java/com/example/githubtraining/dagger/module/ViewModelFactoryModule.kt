package com.example.githubtraining.dagger.module

import android.arch.lifecycle.ViewModelProvider
import com.example.githubtraining.utill.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}