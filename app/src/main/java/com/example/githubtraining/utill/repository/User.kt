package com.example.githubtraining.utill.repository

import android.arch.lifecycle.LiveData
import com.example.githubtraining.database.modelDB.UserInformationModelDB

interface User {

    fun getInfoUserFromDB(listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit) : LiveData<MutableList<UserInformationModelDB>>
    fun getListOfInfoUser():MutableList<UserInformationModelDB>
    fun insertInfoUserIntoDB(movieDB: UserInformationModelDB)
    fun deleteInfoUser()
    fun getUserLogged():String


}