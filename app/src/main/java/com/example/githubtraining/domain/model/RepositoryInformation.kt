package com.example.githubtraining.domain.model

data class RepositoryInformation (
    var id: Int?,
    var name: String?,
    var fullName: String?,
    var createdAt: String?,
    var updatedAt: String?,
    var pushedAt: String?,
    var description: String?,
    var private: Boolean
)