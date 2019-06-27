package com.example.githubtraining.screen.repositories

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.utill.repository.RepositoryUserDB
import javax.inject.Inject

class RepositoriesViewModel(mContext: Context) : ViewModel() {


    @Inject
    lateinit var repository: RepositoriesRepository

    init {
        appComponent.injectRepositoriesViewModel(this)
    }

    var sortNr = repository.sortNrFormDB

    val stuffData = repository.observableDataStuff
    val repoListData = repository.observableDataRepo


    fun getDataWs(encodePass:String) {
        repository.getRepoData(encodePass)
    }

}