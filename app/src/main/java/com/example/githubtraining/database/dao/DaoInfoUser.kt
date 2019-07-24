package com.example.githubtraining.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.githubtraining.database.modelDB.UserInformationModelDB

@Dao
interface DaoInfoUser {

    @Query("select * from userInformationModelDB")
    fun getInfoUser(): LiveData<MutableList<UserInformationModelDB>>

    @Query("select * from userInformationModelDB")
    fun getListOfInfoUser(): MutableList<UserInformationModelDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfoUser(infoUserDB: UserInformationModelDB)

    @Query("delete from userInformationModelDB")
    fun deleteInfoUser()

    @Query("select login from userInformationModelDB")
    fun getUserLogged():String
}