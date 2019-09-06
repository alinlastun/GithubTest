package com.example.githubtraining.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryInformation (

    @SerialName("id")
    var id: Int?,
    @SerialName("name")
    var name: String?,
    @SerialName("full_name")
    var fullName: String?,
    @SerialName("created_at")
    var createdAt: String?,
    @SerialName("updated_at")
    var updatedAt: String?,
    @SerialName("pushed_at")
    var pushedAt: String?,
    @SerialName("description")
    var description: String?,
    @SerialName("private")
    var private: Boolean


)