package com.example.githubtraining.database.dao
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.githubtraining.database.modelDB.StuffModelDB

@Dao
interface DaoStuff {

    @Query("select * from stuffModelDB")
    fun getStuff(): LiveData<StuffModelDB>

    @Query("select * from stuffModelDB")
    fun getStuffList(): MutableList<StuffModelDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStuff(infoStuff: StuffModelDB)

    @Query("UPDATE stuffModelDB SET sort = :mySort WHERE id =1")
    fun updateSort(mySort: Int)

    @Query("select sort from stuffModelDB WHERE id =1")
    fun getSortNumber(): Int

    @Query("select idRadioButton from stuffModelDB WHERE id =1")
    fun getRadioBtnId(): Int

    @Query("delete from stuffModelDB")
    fun deleteStuff()
}