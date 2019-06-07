package com.example.githubtraining.utill.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import com.example.githubtraining.database.AppDataBase
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB

class RepositoryRepoDB(mContext: Context) {
    private var appDB: AppDataBase = AppDataBase.getDataBase(mContext)

    fun getInfoRepoFromDB() : LiveData<MutableList<InfoRepoModelDB>> {
        return appDB.daoInfoRepo().getInfoRepo()
    }

    fun insertInfoRepoIntoDB(repoList: InfoRepoModelDB) {
        AddAsynTask(appDB).execute(repoList)
    }

    fun deleteInfoRepo(){
        appDB.daoInfoRepo().deleteInfoRepo()
    }


    class AddAsynTask(db: AppDataBase) : AsyncTask<InfoRepoModelDB, Void, Void>() {
        private var infoRepoDB = db
        override fun doInBackground(vararg params: InfoRepoModelDB): Void? {
            infoRepoDB.daoInfoRepo().insertInfoRepo(params[0])
            return null
        }
    }
}