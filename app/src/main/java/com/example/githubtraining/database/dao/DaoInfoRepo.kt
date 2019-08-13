package com.example.githubtraining.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubtraining.database.modelDB.InfoRepoModelDB

@Dao
interface  DaoInfoRepo {

    @Query("select * from infoRepoModelDB")
     fun getInfoRepo(): LiveData<List<InfoRepoModelDB>>

    @Query("select * from infoRepoModelDB")
     fun getInfoRepoList(): MutableList<InfoRepoModelDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertInfoRepo(infoRepoDB: List<InfoRepoModelDB>)

    @Query("delete from infoRepoModelDB")
     fun deleteInfoRepo()

    @Query("select * from infoRepoModelDB where id in (:id)")
     fun getRepoById(id: Int): LiveData<InfoRepoModelDB>

    @Query("select * from infoRepoModelDB order by full_name asc")
     fun getRepoSortedByFullName(): List<InfoRepoModelDB>

    @Query("select * from infoRepoModelDB order by created_at asc")
     fun getRepoSortedByCreated(): List<InfoRepoModelDB>

    @Query("select * from infoRepoModelDB order by updated_at asc")
     fun getRepoSortedByUpdated(): List<InfoRepoModelDB>

    @Query("select * from infoRepoModelDB order by pushed_at asc")
     fun getRepoSortedByPushed():List<InfoRepoModelDB>

   

}