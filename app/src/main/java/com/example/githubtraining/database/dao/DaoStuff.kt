package com.example.githubtraining.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.StuffModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB

@Dao
interface DaoStuff {

    @Query("select * from stuff_table")
    fun getStuff(): LiveData<StuffModelDB>

    @Query("select * from stuff_table")
    fun getStuffList(): MutableList<StuffModelDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStuff(infoStuff:  StuffModelDB)

    @Query("UPDATE stuff_table SET sort = :mySort WHERE _id =1")
    fun updateSort(mySort: Int)

    @Query("select sort from stuff_table WHERE _id =1")
    fun getSortNumber(): Int


    @Query("select idRadioButton from stuff_table WHERE _id =1")
    fun getRadioBtnId(): Int

    @Query("delete from stuff_table")
    fun deleteStuff()

}