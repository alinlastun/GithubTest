package com.example.githubtraining.screen.repoDetails

import android.arch.lifecycle.LiveData
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.utill.repository.RepositoryRepoDB
import javax.inject.Inject


 object RepoDetailsRepository {

    var repositoryRepoDB: RepositoryRepoDB = appComponent.getRepositoryRepoDB()
        @Inject set

    fun getRepoById(repoId:Int): LiveData<InfoRepoModelDB> {
        return repositoryRepoDB.getInfoRepoById(repoId)
    }
}