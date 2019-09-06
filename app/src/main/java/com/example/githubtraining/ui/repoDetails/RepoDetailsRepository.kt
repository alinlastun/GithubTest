package com.example.githubtraining.ui.repoDetails

import androidx.lifecycle.LiveData
import com.example.githubtraining.db.dao.DaoInfoRepo
import com.example.githubtraining.db.model.InfoRepoModelDB
import javax.inject.Inject

 class RepoDetailsRepository @Inject constructor(private val daoInfoRepo: DaoInfoRepo) {

    fun getRepoById(repoId: Int): LiveData<InfoRepoModelDB> = daoInfoRepo.getRepoById(repoId)
    fun getRepos():  List<InfoRepoModelDB> = daoInfoRepo.getInfoRepoList()
}