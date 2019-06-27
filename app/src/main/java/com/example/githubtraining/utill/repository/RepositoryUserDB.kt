package com.example.githubtraining.utill.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.AppDataBase
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import javax.inject.Inject

class RepositoryUserDB @Inject constructor(private val appDB:AppDataBase) {


    fun getInfoUserFromDB() : LiveData<MutableList<UserInformationModelDB>> {
        return appDB.daoInfoUser().getInfoUser()
    }

    fun getListOfInfoUser():MutableList<UserInformationModelDB>{
        return appDB.daoInfoUser().getListOfInfoUser()
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