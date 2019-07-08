package com.example.githubtraining.utill.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.AppDataBase
import com.example.githubtraining.database.dao.DaoInfoRepo
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import com.example.githubtraining.screen.repositories.RepositoriesRepository
import javax.inject.Inject

class RepositoryRepoDB @Inject constructor(private val daoInfoRepo:DaoInfoRepo, private val repositoriesRepository: RepositoriesRepository) :Repo{




    override fun getInfoRepoById(repoId: Int): LiveData<InfoRepoModelDB> {
        return daoInfoRepo.getRepoById(repoId)
    }

    override fun deleteInfoRepo() {
        daoInfoRepo.deleteInfoRepo()
    }

    override fun getLiveDataInfoRepo(): LiveData<MutableList<InfoRepoModelDB>> {
        // refresh from network
        return daoInfoRepo.getInfoRepo()
    }

    override fun getListInfoRepo(): MutableList<InfoRepoModelDB> {
        return daoInfoRepo.getInfoRepoList()
    }

    override fun insertInfoRepo(repoList: List<InfoRepoModelDB>) {
        AddAsynTask(daoInfoRepo).execute(repoList)
    }

    class AddAsynTask(private val daoInfoRepo:DaoInfoRepo) : AsyncTask<List<InfoRepoModelDB>, Void, Void>() {
        override fun doInBackground(vararg params: List<InfoRepoModelDB>): Void? {
            daoInfoRepo.insertInfoRepo(params[0])
            return null
        }
    }
}