package com.example.githubtraining.database.modelDB

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "repoInfo_table", indices = [android.arch.persistence.room.Index(value = ["id"], unique = true)])

data class InfoRepoModelDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_table")
    var id_table: Int = 0,

    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "full_name")
    var full_name: String?,

    @ColumnInfo(name = "created_at")
    var created_at: String?,

    @ColumnInfo(name = "updated_at")
    var updated_at: String?,

    @ColumnInfo(name = "pushed_at")
    var pushed_at: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "private")
    var typeRepo: Boolean?


)