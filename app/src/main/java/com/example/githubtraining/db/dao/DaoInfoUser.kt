package com.example.githubtraining.db.dao

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.githubtraining.db.model.UserInformation
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DaoInfoUser {

    @Query("select * from userInformation")
    abstract fun getAllInfoUser(): Flow<UserInformation>

    @Query("select * from userInformation")
    abstract fun getListOfInfoUser(): UserInformation

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertInfoUser(entity: UserInformation)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateInfoUser(entity: UserInformation)

    @Query("delete from userInformation")
    abstract fun deleteInfoUser()

    @Query("select login from userInformation")
    abstract fun getUserLogged(): String

    @Transaction
    open fun upsert(entity: UserInformation) {
        insertInfoUser(entity)
        Log.d("TimberExtensionsKt", "Add campaign into DB2")
        updateInfoUser(entity)
        Log.d("TimberExtensionsKt", "Add campaign into DB3")
    }
}