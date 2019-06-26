package com.example.githubtraining.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB

@Dao
interface DaoInfoRepo {

    @Query("select * from repoInfo_table")
    fun getInfoRepo(): LiveData<MutableList<InfoRepoModelDB>>

    @Query("select * from repoInfo_table")
    fun getInfoRepoList(): MutableList<InfoRepoModelDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfoRepo(infoRepoDB:  List<InfoRepoModelDB>)

    @Query("delete from repoInfo_table")
    fun deleteInfoRepo()

    @Query("select * from repoInfo_table where id in (:id)")
    fun getRepoById(id: Int): LiveData<InfoRepoModelDB>
}