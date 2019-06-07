package com.example.githubtraining.database.modelDB

import android.arch.persistence.room.*
import com.example.githubtraining.database.GithubTypeConverters
import com.example.githubtraining.model.RepoModel

@Entity(tableName = "repoInfo_table")
@TypeConverters(GithubTypeConverters::class)
data class InfoRepoModelDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @Embedded(prefix = "repoInfo")
    var repoInfo: RepoModel

    )