package com.example.githubtraining.screen.repositories

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.utill.repository.RepositoryUserDB

class RepositoriesViewModel(mContext: Context) : ViewModel() {


    private val repository = RepositoriesRepository(mContext)

    var sortNr = repository.sortNrFormDB

    val stuffData = repository.observableDataStuff
    val repoListData = repository.observableDataRepo


    fun getDataWs() {
        repository.getRepoData(repository.mEncodedUserPass)
    }

}