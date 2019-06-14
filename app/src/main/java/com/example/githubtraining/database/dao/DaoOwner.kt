package com.example.githubtraining.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.githubtraining.database.modelDB.OwnerModelDB


interface DaoOwner {
    @Query("select * from owner_table")
    fun getOwner(): LiveData<MutableList<OwnerModelDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOwner(infoRepoDB:  OwnerModelDB)

    @Query("select * from owner_table where id in (:ownerId)")
    fun getOwnerById(ownerId: Int): LiveData<OwnerModelDB>

    @Query("delete from owner_table")
    fun deleteOwner()

}