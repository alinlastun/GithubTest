package com.example.githubtraining.screen.infoUser

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import com.example.githubtraining.appComponent
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

    var mValuesDataBase = mRepositoryUser.getInfoUserFromDB()

    fun deleteInfoUserFromDB(){
        mRepositoryUser.deleteInfoUser()
    }

    fun deleteInfoRepo(){
        mRepositoryRepo.deleteInfoRepo()
    }

}