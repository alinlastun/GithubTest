package com.example.githubtraining.screen.repoDetails

import android.arch.lifecycle.LiveData
import android.content.Context
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.utill.repository.RepositoryRepoDB

class RepoDetailsRepository(mContext: Context){
    private val repositoryRepoDB = RepositoryRepoDB(mContext)

    fun getRepoById(repoId:Int): LiveData<InfoRepoModelDB> {
        return repositoryRepoDB.getInfoRepoById(repoId)
    }
}