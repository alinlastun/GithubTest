package com.example.githubtraining.db.dao

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.githubtraining.db.model.RepositoryInformation
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DaoInfoRepo {

    @Query("select * from repositoryInformation")
    abstract fun getInfoRepo(): Flow<List<RepositoryInformation>>

    @Query("select * from repositoryInformation")
    abstract fun getInfoRepoList(): List<RepositoryInformation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertInfoRepo(entity: List<RepositoryInformation>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateInfoRepo(entity: List<RepositoryInformation>)

    @Query("delete from repositoryInformation")
    abstract fun deleteInfoRepo()

    @Query("select * from repositoryInformation where id in (:id)")
    abstract fun getRepoById(id: Int): Flow<RepositoryInformation>

    @Query("select * from repositoryInformation order by fullName asc")
    abstract fun getRepoSortedByFullName(): Flow<List<RepositoryInformation>>

    @Query("select * from repositoryInformation order by createdAt asc")
    abstract fun getRepoSortedByCreated(): Flow<List<RepositoryInformation>>

    @Query("select * from repositoryInformation order by updatedAt asc")
    abstract fun getRepoSortedByUpdated(): Flow<List<RepositoryInformation>>

    @Query("select * from repositoryInformation order by pushedAt asc")
    abstract fun getRepoSortedByPushed(): Flow<List<RepositoryInformation>>

    @Transaction
    open fun upsert(entity: List<RepositoryInformation>) {
        insertInfoRepo(entity)
        Log.d("TimberExtensionsKt", "Add campaign into DB2")
        updateInfoRepo(entity)
        Log.d("TimberExtensionsKt", "Add campaign into DB3")
    }

}