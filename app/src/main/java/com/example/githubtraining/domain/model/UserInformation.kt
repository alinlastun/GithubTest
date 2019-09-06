package com.example.githubtraining.domain.model

data class UserInformation(
    val id: Int?,
    val avatarUrl: String?,
    val bio: String?,
    val location: String?,
    val email: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val privateRepos: Int?,
    val publicRepos: Int?,
    val login: String?
)