package com.example.githubtraining.utill.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import com.example.githubtraining.database.AppDataBase
import com.example.githubtraining.database.modelDB.UserInformationModelDB

class RepositoryUserDB(mContext: Context) {
    private var appDB: AppDataBase = AppDataBase.getDataBase(mContext)

    fun getInfoUserFromDB() : LiveData<MutableList<UserInformationModelDB>> {
        return appDB.daoInfoUser().getInfoUser()
    }

    fun insertInfoUserIntoDB(movieDB: UserInformationModelDB) {
        AddAsynTask(appDB).execute(movieDB)
    }

    fun deleteInfoUser(){
        appDB.daoInfoUser().deleteInfoUser()
    }


    fun getEncodedUserPass():String{
        return appDB.daoInfoUser().getEncodeUserPass()
    }

    class AddAsynTask(db: AppDataBase) : AsyncTask<UserInformationModelDB, Void, Void>() {
        private var infoUserDB = db
        override fun doInBackground(vararg params: UserInformationModelDB): Void? {
            infoUserDB.daoInfoUser().insertInfoUser(params[0])
            return null
        }
    }
}