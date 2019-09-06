package com.example.githubtraining.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInformation(
    @PrimaryKey
    val id: Int?,
    val avatarUrl: String?,
    val bio: String?,
    val location: String?,
    val email: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val publicRepos: Int?,
    val privateRepos: Int?,
    val login: String?

)
