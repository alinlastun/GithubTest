package com.example.githubtraining.model

import com.example.githubtraining.database.modelDB.InfoRepoModelDB

class RepoItem (repo: InfoRepoModelDB, override var myCreatedAt: String) : DisplayRepo {

    var id: Int? =repo.id
    var name: String? = repo.name
    var full_name: String?=repo.full_name
    var created_at: String?=repo.created_at
    var updated_at: String?=repo.updated_at
    var pushed_at: String?=repo.pushed_at
    var description: String?=repo.description
    var private: Boolean?=repo.private
}