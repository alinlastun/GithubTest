package com.example.githubtraining.screen.repositories

import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(private val repository: RepositoriesRepository) : ViewModel() {

    var sortNr = repository.sortNrFormDB

    val stuffData = repository.observableDataStuff
    val repoListData = repository.observableDataRepo
    val stuffList=repository.stuffDbList

    fun getDataWs(encodePass:String) {
        repository.getRepoData(encodePass)
    }

}