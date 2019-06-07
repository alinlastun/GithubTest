package com.example.githubtraining.screen.repositories

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.utill.repository.RepositoryUserDB

class RepositoriesViewModel(mContext:Context) : ViewModel() {

    private val mRepository = RepositoryUserDB(mContext)
    private val repository = RepositoriesRepository(mContext,this)


    var mEncodedUserPass:String = mRepository.getEncodedUserPass()



    fun getDataWs() {
        repository.getRepoData(mEncodedUserPass)
    }

}