package com.example.githubtraining.screen.infoUser

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.support.annotation.WorkerThread
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import com.example.githubtraining.utill.repository.RepositoryRepoDB
import com.example.githubtraining.utill.repository.RepositoryUserDB
import javax.inject.Inject

class InfoUserViewModel @Inject constructor(private val mRepositoryUser: RepositoryUserDB,private val mRepositoryRepo: RepositoryRepoDB) : ViewModel() {

    val mUrlAvatar = ObservableField("")
    val mBio = ObservableField("")
    val mLocation = ObservableField("")
    val mEmail = ObservableField("")
    val mCreated = ObservableField("")
    val mUpdated = ObservableField("")
    val mPublicRepo = ObservableField("")
    val mPrivateRepo = ObservableField("")

    lateinit var userInfo:LiveData<UserInformationModelDB>

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


    var mGetInfoUser = mRepositoryUser.getInfoUserFromDB{_, _, _ ->}

    @WorkerThread
    fun deleteInfoUserFromDB(){
        mRepositoryUser.deleteInfoUser()
    }
    @WorkerThread
    fun deleteInfoRepo(){
        mRepositoryRepo.deleteInfoRepo()
    }



}