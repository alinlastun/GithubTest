package com.example.githubtraining.utill.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.AppDataBase
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import javax.inject.Inject

class RepositoryRepoDB @Inject constructor(private val appDB:AppDataBase) :Repo{


    override fun getInfoRepoById(repoId: Int): LiveData<InfoRepoModelDB> {
        return appDB.daoInfoRepo().getRepoById(repoId)
    }

    override fun deleteInfoRepo() {
        appDB.daoInfoRepo().deleteInfoRepo()
    }

    override fun getLiveDataInfoRepo(): LiveData<MutableList<InfoRepoModelDB>> {
        return appDB.daoInfoRepo().getInfoRepo()
    }

    override fun getListInfoRepo(): MutableList<InfoRepoModelDB> {
        return appDB.daoInfoRepo().getInfoRepoList()
    }

    override fun insertInfoRepo(repoList: List<InfoRepoModelDB>) {
        AddAsynTask(appDB).execute(repoList)
    }


    class AddAsynTask(db: AppDataBase) : AsyncTask<List<InfoRepoModelDB>, Void, Void>() {
        private var infoRepoDB = db
        override fun doInBackground(vararg params: List<InfoRepoModelDB>): Void? {
            infoRepoDB.daoInfoRepo().insertInfoRepo(params[0])
            return null
        }
    }
}