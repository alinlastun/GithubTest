package com.example.githubtraining.ui.infoUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.githubtraining.database.dao.DaoInfoRepo
import com.example.githubtraining.database.dao.DaoInfoUser
import com.example.githubtraining.utilities.Tools
import javax.inject.Inject

class InfoUserViewModel @Inject constructor(
    private val mDaoInfoRepo: DaoInfoRepo,
    private val daoInfoUser: DaoInfoUser
) : ViewModel() {
    val mUrlAvatar: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        repo.avatar_url
    }
    val mBio: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        checkString(repo.bio)
    }
    val mLocation: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        checkString(repo.location)
    }
    val mEmail: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        checkString(repo.email)
    }
    val mCreated: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        Tools().formatMyDate(checkString(repo.created_at))
    }
    val mUpdated: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        Tools().formatMyDate(checkString(repo.updated_at))
    }
    val mPublicRepo: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        checkString(repo.total_private_repos.toString())
    }
    val mPrivateRepo: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        checkString(repo.public_repos.toString())
    }

    private fun checkString(value: String?): String {
        var myString: String? = value
        if (value == null) {
           myString = "No Information"
        }
        return myString!!
    }

}