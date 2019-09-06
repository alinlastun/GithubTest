package com.example.githubtraining.repository.apimapper

import com.example.githubtraining.api.model.UserInformation

object LoginMapper {
    fun map(userInfo: UserInformation) =
        com.example.githubtraining.db.model.UserInformation (
            userInfo.id,
            userInfo.avatarUrl,
            userInfo.bio,
            userInfo.location,
            userInfo.email,
            userInfo.createdAt,
            userInfo.updatedAt,
            userInfo.privateRepos,
            userInfo.publicRepos,
            userInfo.login
    )
}