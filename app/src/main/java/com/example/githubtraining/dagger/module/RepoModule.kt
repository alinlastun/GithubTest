package com.example.githubtraining.dagger.module

import com.example.githubtraining.utill.repository.Repo
import com.example.githubtraining.utill.repository.RepositoryRepoDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun provideRepositoryRepo(): Repo = RepositoryRepoDB()
}