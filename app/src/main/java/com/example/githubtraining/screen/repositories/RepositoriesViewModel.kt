package com.example.githubtraining.screen.repositories

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(private val repository: RepositoriesRepository) : ViewModel() {

    var sortNr = repository.sortNrFormDB

    val stuffData = repository.observableDataStuff
    val repoListData = repository.observableDataRepo
    val repoList =repository.repoList

    fun getDataWs(encodePass:String) {
        repository.getRepoData(encodePass)
    }

}