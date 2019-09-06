package com.example.githubtraining.repository.apimapper

import com.example.githubtraining.db.model.RepositoryInformation

object RepoInfoMapper {
    fun map(repoInfo: RepositoryInformation)=com.example.githubtraining.domain.model.RepositoryInformation(
        repoInfo.id,
        repoInfo.fullName,
        repoInfo.name,
        repoInfo.createdAt,
        repoInfo.updatedAt,
        repoInfo.description,
        repoInfo.pushedAt,
        repoInfo.private
        )
}