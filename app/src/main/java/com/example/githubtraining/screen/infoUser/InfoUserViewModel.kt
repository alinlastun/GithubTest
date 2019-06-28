package com.example.githubtraining.screen.infoUser

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import com.example.githubtraining.appComponent
import com.example.githubtraining.utill.repository.RepositoryUserDB
import javax.inject.Inject

class InfoUserViewModel @Inject constructor(private val mRepository: RepositoryUserDB) : ViewModel() {

    val mUrlAvatar = ObservableField("")
    val mBio = ObservableField("")
    val mLocation = ObservableField("")
    val mEmail = ObservableField("")
    val mCreated = ObservableField("")
    val mUpdated = ObservableField("")
    val mPublicRepo = ObservableField("")
    val mPrivateRepo = ObservableField("")

    var mValuesDataBase = mRepository.getInfoUserFromDB()
    val mListInfoUser = mRepository.getListOfInfoUser()

    fun deleteInfoUserFromDB(){
        mRepository.deleteInfoUser()
    }

}