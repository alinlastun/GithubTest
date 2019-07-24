package com.example.githubtraining.database.modelDB

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class StuffModelDB constructor (

    @PrimaryKey
    var id:Int=1,
    var sort: Int = 1,
    var owner: Boolean = true,
    var collaborator: Boolean = false,
    var organizationMember: Boolean = false,
    var idRadioButton: Int = -1

    )