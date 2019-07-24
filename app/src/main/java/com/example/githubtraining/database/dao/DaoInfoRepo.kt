package com.example.githubtraining.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubtraining.database.modelDB.InfoRepoModelDB

@Dao
interface DaoInfoRepo {

    @Query("select * from infoRepoModelDB")
    fun getInfoRepo(): LiveData<MutableList<InfoRepoModelDB>>

    @Query("select * from infoRepoModelDB")
    fun getInfoRepoList(): MutableList<InfoRepoModelDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfoRepo(infoRepoDB:  List<InfoRepoModelDB>)

    @Query("delete from infoRepoModelDB")
    fun deleteInfoRepo()

    @Query("select * from infoRepoModelDB where id in (:id)")
    fun getRepoById(id: Int): LiveData<InfoRepoModelDB>
}