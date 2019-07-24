package com.example.githubtraining.ui.repoDetails

import androidx.lifecycle.LiveData
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.utill.repository.RepositoryRepoDB
import javax.inject.Inject


 class RepoDetailsRepository @Inject constructor(private val repositoryRepoDB: RepositoryRepoDB) {

    fun getRepoById(repoId:Int): LiveData<InfoRepoModelDB> = repositoryRepoDB.getInfoRepoById(repoId)
}