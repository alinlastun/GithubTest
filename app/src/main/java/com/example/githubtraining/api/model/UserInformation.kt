package com.example.githubtraining.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInformation(
    @SerialName("id")
    var id: Int?,
    @SerialName("avatar_url")
    var avatarUrl: String?,
    @SerialName("bio")
    var bio: String?,
    @SerialName("location")
    var location: String?,
    @SerialName("email")
    var email: String?,
    @SerialName("created_at")
    var createdAt: String?,
    @SerialName("updated_at")
    var updatedAt: String?,
    @SerialName("total_private_repos")
    var privateRepos: Int?,
    @SerialName("public_repos")
    var publicRepos: Int?,
    @SerialName("login")
    var login: String?
)

