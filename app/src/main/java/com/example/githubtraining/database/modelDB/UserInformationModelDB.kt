package com.example.githubtraining.database.modelDB

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "userInfo_table")
data class UserInformationModelDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_")
    var id: Int = 0,

    @ColumnInfo(name = "avatar_url")
    var avatar_url: String?,

    @ColumnInfo(name = "bio")
    var bio: String?,

    @ColumnInfo(name = "location")
    var location: String?,

    @ColumnInfo(name = "email")
    var email: String?,

    @ColumnInfo(name = "created_at")
    var created_at: String?,

    @ColumnInfo(name = "updated_at")
    var updated_at: String?,

    @ColumnInfo(name = "public_repos")
    var public_repos: Int?,

    @ColumnInfo(name = "total_private_repos")
    var total_private_repos: Int?,

    @ColumnInfo(name = "login")
    var login: String?,

    @ColumnInfo(name = "isLogin")
    var isLogin: Boolean = false,

    @ColumnInfo(name = "encodedUserPass")
    var encodedUserPass: String?


)
