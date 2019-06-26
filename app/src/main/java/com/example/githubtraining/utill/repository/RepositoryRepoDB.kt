package com.example.githubtraining.utill.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.AppDataBase
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import javax.inject.Inject

class RepositoryRepoDB(mContext: Context) {
    @Inject
    lateinit var appDB:AppDataBase


    init {
        appComponent.inject(this)
    }

    fun getInfoRepoFromDB() : LiveData<MutableList<InfoRepoModelDB>> {
        return appDB.daoInfoRepo().getInfoRepo()
    }

    fun getInfoRepoFromDBList() : MutableList<InfoRepoModelDB> {
        return appDB.daoInfoRepo().getInfoRepoList()
    }

    fun insertInfoRepoIntoDB(repoList: List<InfoRepoModelDB>) {
        AddAsynTask(appDB).execute(repoList)
    }

    fun deleteInfoRepo(){
        appDB.daoInfoRepo().deleteInfoRepo()
    }

    fun getInfoRepoById(repoId:Int):LiveData<InfoRepoModelDB>{
       return appDB.daoInfoRepo().getRepoById(repoId)
    }


    class AddAsynTask(db: AppDataBase) : AsyncTask<List<InfoRepoModelDB>, Void, Void>() {
        private var infoRepoDB = db
        override fun doInBackground(vararg params: List<InfoRepoModelDB>): Void? {
            infoRepoDB.daoInfoRepo().insertInfoRepo(params[0])
            return null
        }
    }
}