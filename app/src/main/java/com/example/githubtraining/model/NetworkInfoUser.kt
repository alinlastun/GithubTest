package com.example.githubtraining.model

import com.example.githubtraining.database.modelDB.UserInformationModelDB

data class NetworkInfoUser(
    var id: Int?,
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

fun NetworkInfoUser.asInfoUserDBModel(): UserInformationModelDB {
    return UserInformationModelDB(
        avatar_url = avatar_url,
        created_at = created_at,
        updated_at = updated_at,
        total_private_repos = total_private_repos,
        public_repos = public_repos,
        location = location,
        isLogin = isLogin,
        email = email,
        encodedUserPass = encodedUserPass,
        bio = bio,
        login = login,
        id = id
    )}