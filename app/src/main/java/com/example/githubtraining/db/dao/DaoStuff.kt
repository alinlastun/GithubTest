package com.example.githubtraining.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.githubtraining.db.model.StuffModelDB
import com.example.githubtraining.model.Member

@Dao
abstract class DaoStuff {

    @Query("select * from stuffModelDB")
    abstract fun getStuff(): LiveData<StuffModelDB>

    @Query("select * from stuffModelDB")
    abstract fun getStuffList(): List<StuffModelDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertStuff(infoStuff: StuffModelDB)

    @Query("UPDATE stuffModelDB SET sort = :mySort WHERE id =1")
    abstract fun updateSort(mySort: Int)

    @Query("select sort from stuffModelDB WHERE id =1")
    abstract fun getSortNumber(): Int

    @Query("select idRadioButton from stuffModelDB WHERE id =1")
    abstract fun getRadioBtnId(): Int

    @Query("delete from stuffModelDB")
    abstract fun deleteStuff()

    @Query("select owner from stuffModelDB")
    abstract fun getOwner(): Boolean

    @Query("select collaborator from stuffModelDB")
    abstract fun getCollaborator(): Boolean

    @Query("select organizationMember from stuffModelDB")
    abstract fun getOrganizationMember(): Boolean

    @Transaction
    open fun getMembers() :List<Member>{
        val list = mutableListOf<Member>()
        list.add(Member(getOwner(),getCollaborator(),getOrganizationMember()))
        return list
    }

}