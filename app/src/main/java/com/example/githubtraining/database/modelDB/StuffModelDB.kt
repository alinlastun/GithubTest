package com.example.githubtraining.database.modelDB

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "stuff_table")
data class StuffModelDB (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var _id: Int = 1,
    var sort: Int = 1,
    var idRadioButton: Int = -1

    )