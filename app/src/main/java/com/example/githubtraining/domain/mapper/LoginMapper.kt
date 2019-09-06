package com.example.githubtraining.domain.mapper

import com.example.githubtraining.db.model.UserInformation

object LoginMapper {
    fun map(infoUser: UserInformation) =
        com.example.githubtraining.domain.model.UserInformation(
            infoUser.id,
            infoUser.avatarUrl,
            infoUser.bio,
            infoUser.location,
            infoUser.email,
            infoUser.createdAt,
            infoUser.updatedAt,
            infoUser.privateRepos,
            infoUser.publicRepos,
            infoUser.login
        )
}