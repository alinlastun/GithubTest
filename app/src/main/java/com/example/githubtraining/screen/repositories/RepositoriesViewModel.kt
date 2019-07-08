package com.example.githubtraining.screen.repositories

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(private val repository: RepositoriesRepository) : ViewModel() {

    var sortNr = repository.sortNrFormDB

    val stuffData = repository.observableDataStuff
    val repoListData = repository.observableDataRepo
    val stuffList=repository.stuffDbList
    val mSuccessReceive = MutableLiveData<Boolean>()
    val mErrorReceive = MutableLiveData<Boolean>()
    val mErrorMsgReceive =ObservableField("")
    val userNameLogged= repository.infoUserLogged



    fun getDataWs() {
        repository.getRepoData{success, error, errorMsg->
            when{
                success-> mSuccessReceive.value=true
                error-> {
                    mErrorMsgReceive.set(errorMsg)
                    mErrorReceive.value=true
                }

            }

        }
    }

}