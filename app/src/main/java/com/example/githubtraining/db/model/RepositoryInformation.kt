package com.example.githubtraining.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepositoryInformation(

    @PrimaryKey
    var id: Int?,
    var name: String?,
    var fullName: String?,
    var createdAt: String?,
    var updatedAt: String?,
    var description: String?,
    var pushedAt: String?,
    var private: Boolean

)
