package com.example.githubtraining.screen.infoUser

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import com.example.githubtraining.utill.repository.RepositoryUserDB

class InfoUserViewModel(mContext:Context) : ViewModel() {

    val mUrlAvatar = ObservableField("")
    val mBio = ObservableField("")
    val mLocation = ObservableField("")
    val mEmail = ObservableField("")
    val mCreated = ObservableField("")
    val mUpdated = ObservableField("")
    val mPublicRepo = ObservableField("")
    val mPrivateRepo = ObservableField("")

    private val mRepository = RepositoryUserDB(mContext)
    var mValuesDataBase = mRepository.getInfoUserFromDB()

    fun deleteInfoUserFromDB(){
        mRepository.deleteInfoUser()
    }

}