package com.example.githubtraining.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.githubtraining.database.modelDB.UserInformationModelDB

@Dao
interface DaoInfoUser {

    @Query("select * from userInfo_table")
    fun getInfoUser(): LiveData<MutableList<UserInformationModelDB>>

    @Query("select * from userInfo_table")
    fun getListOfInfoUser(): MutableList<UserInformationModelDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfoUser(infoUserDB: UserInformationModelDB)

    @Query("delete from userInfo_table")
    fun deleteInfoUser()

    @Query("select encodedUserPass from userInfo_table")
    fun getEncodeUserPass():String
}