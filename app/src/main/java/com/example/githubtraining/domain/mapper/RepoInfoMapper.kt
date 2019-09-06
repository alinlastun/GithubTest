package com.example.githubtraining.domain.mapper

import com.example.githubtraining.db.model.RepositoryInformation

object RepoInfoMapper {

    fun map(infoRepo: RepositoryInformation)=com.example.githubtraining.domain.model.RepositoryInformation(
        infoRepo.id,
        infoRepo.fullName,
        infoRepo.name,
        infoRepo.createdAt,
        infoRepo.updatedAt,
        infoRepo.pushedAt,
        infoRepo.description,
        infoRepo.private
    )
}