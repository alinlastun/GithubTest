package com.example.githubtraining.utill.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import com.example.githubtraining.database.AppDataBase
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.OwnerModelDB
import com.example.githubtraining.database.modelDB.StuffModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB

class RepositoryOwnerDB(mContext: Context) {
    private var appDB: AppDataBase = AppDataBase.getDataBase(mContext)

    fun getOwnerFromDB() : LiveData<MutableList<OwnerModelDB>> {
        return appDB.daoOwner().getOwner()
    }

    fun insertOwnerIntoDB(repoList: OwnerModelDB) {
        AddAsynTask(appDB).execute(repoList)
    }

    fun deleteOwner(){
        appDB.daoInfoRepo().deleteInfoRepo()
    }

    fun getOwnerById(OwnerId:Int):LiveData<OwnerModelDB>{
        return appDB.daoOwner().getOwnerById(OwnerId)
    }


    class AddAsynTask(db: AppDataBase) : AsyncTask<OwnerModelDB, Void, Void>() {
        private var infoUserDB = db
        override fun doInBackground(vararg params: OwnerModelDB): Void? {
            infoUserDB.daoOwner().insertOwner(params[0])
            return null
        }
    }
}