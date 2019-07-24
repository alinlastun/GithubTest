package com.example.githubtraining.database.modelDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInformationModelDB constructor(
    @PrimaryKey
    var id:Int?,
    var avatar_url: String?,
    var bio: String?,
    var location: String?,
    var email: String?,
    var created_at: String?,
    var updated_at: String?,
    var public_repos: Int?,
    var total_private_repos: Int?,
    var login: String?,
    var isLogin: Boolean = false,
    var encodedUserPass: String?

)
