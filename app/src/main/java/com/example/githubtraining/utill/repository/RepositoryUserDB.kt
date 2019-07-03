package com.example.githubtraining.utill.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.AppDataBase
import com.example.githubtraining.database.dao.DaoInfoUser
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import javax.inject.Inject

class RepositoryUserDB @Inject constructor(private val daoInfoUser:DaoInfoUser):User {


    override fun getInfoUserFromDB() : LiveData<MutableList<UserInformationModelDB>> {
        return daoInfoUser.getInfoUser()
    }

    override fun getListOfInfoUser():MutableList<UserInformationModelDB>{
        return daoInfoUser.getListOfInfoUser()
    }

    override fun insertInfoUserIntoDB(movieDB: UserInformationModelDB) {
        AddAsynTask(daoInfoUser).execute(movieDB)
    }

    override fun deleteInfoUser(){
        daoInfoUser.deleteInfoUser()
    }

    override fun getUserLogged():String{
        return daoInfoUser.getUserLogged()
    }

    class AddAsynTask(private val daoInfoUser:DaoInfoUser) : AsyncTask<UserInformationModelDB, Void, Void>() {
        override fun doInBackground(vararg params: UserInformationModelDB): Void? {
            daoInfoUser.insertInfoUser(params[0])
            return null
        }
    }
}