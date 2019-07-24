package com.example.githubtraining.database.modelDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InfoRepoModelDB constructor(
    @PrimaryKey
    var id: Int,
    var name: String?,
    var full_name: String?,
    var created_at: String?,
    var updated_at: String?,
    var pushed_at: String?,
    var description: String?,
    var private: Boolean?
){
    constructor() : this(0, "", "", "", "", "", "", false)
}