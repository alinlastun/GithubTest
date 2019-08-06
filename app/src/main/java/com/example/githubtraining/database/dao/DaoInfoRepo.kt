package com.example.githubtraining.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubtraining.database.modelDB.InfoRepoModelDB

@Dao
abstract class DaoInfoRepo {

    @Query("select * from infoRepoModelDB")
    abstract fun getInfoRepo(): LiveData<List<InfoRepoModelDB>>

    @Query("select * from infoRepoModelDB")
    abstract fun getInfoRepoList(): MutableList<InfoRepoModelDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertInfoRepo(infoRepoDB: List<InfoRepoModelDB>)

    @Query("delete from infoRepoModelDB")
    abstract fun deleteInfoRepo()

    @Query("select * from infoRepoModelDB where id in (:id)")
    abstract fun getRepoById(id: Int): LiveData<InfoRepoModelDB>

    @Query("select * from infoRepoModelDB order by full_name asc")
    abstract fun getRepoSortedByFullName(): LiveData<List<InfoRepoModelDB>>

    @Query("select * from infoRepoModelDB order by created_at asc")
    abstract fun getRepoSortedByCreated(): LiveData<List<InfoRepoModelDB>>

    @Query("select * from infoRepoModelDB order by updated_at asc")
    abstract fun getRepoSortedByUpdated(): LiveData<List<InfoRepoModelDB>>

    @Query("select * from infoRepoModelDB order by pushed_at asc")
    abstract fun getRepoSortedByPushed(): LiveData<List<InfoRepoModelDB>>

   
    open fun getRepoListSorted(resultSort: Int): LiveData<List<InfoRepoModelDB>> {
        return when (resultSort) {
            0 -> getRepoSortedByCreated()
            1 -> getRepoSortedByUpdated()
            2 -> getRepoSortedByPushed()
            3 -> getRepoSortedByFullName()
            else -> throw Exception("Unknown InfoRepoModelDB")
        }
    }
}