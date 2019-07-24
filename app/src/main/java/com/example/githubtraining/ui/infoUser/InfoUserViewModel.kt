package com.example.githubtraining.ui.infoUser

import androidx.annotation.WorkerThread
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubtraining.database.dao.DaoInfoRepo
import com.example.githubtraining.database.dao.DaoInfoUser
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import javax.inject.Inject

class InfoUserViewModel @Inject constructor(private val mDaoInfoRepo: DaoInfoRepo,val daoInfoUser: DaoInfoUser) : ViewModel() {

    val mUrlAvatar = ObservableField("")
    val mBio = ObservableField("")
    val mLocation = ObservableField("")
    val mEmail = ObservableField("")
    val mCreated = ObservableField("")
    val mUpdated = ObservableField("")
    val mPublicRepo = ObservableField("")
    val mPrivateRepo = ObservableField("")

    lateinit var userInfo: LiveData<UserInformationModelDB>

/*
    val nUrlAvatar:LiveData<String> = Transformations.map(userInfo) { repo ->
        repo.avatar_url
    }
    val nBio:LiveData<String> = Transformations.map(userInfo) { repo ->
        repo.bio
    }
    val nLocation:LiveData<String> = Transformations.map(userInfo) { repo ->
        repo.location
    }
    val nEmail:LiveData<String> = Transformations.map(userInfo) { repo ->
        repo.email
    }
    val nCreated:LiveData<String> = Transformations.map(userInfo) { repo ->
        repo.created_at
    }
    val nUpdated:LiveData<String> = Transformations.map(userInfo) { repo ->
        repo.updated_at
    }
    val nPrivateRepo:LiveData<String> = Transformations.map(userInfo) { repo ->
        repo.total_private_repos.toString()
    }
*/


    var mGetInfoUser = daoInfoUser.getInfoUser()

    @WorkerThread
    fun deleteInfoUserFromDB(){
        daoInfoUser.deleteInfoUser()
    }
    @WorkerThread
    fun deleteInfoRepo(){
        mDaoInfoRepo.deleteInfoRepo()
    }



}