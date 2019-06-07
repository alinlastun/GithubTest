package com.example.githubtraining.database.modelDB

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "repoInfo_table",indices = [android.arch.persistence.room.Index(value = ["id"],unique = true)])

data class InfoRepoModelDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_table")
    var id_table: Int = 0,

    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "description")
    var description: String?


    )