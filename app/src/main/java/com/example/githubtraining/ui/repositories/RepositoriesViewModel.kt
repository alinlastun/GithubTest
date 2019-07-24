package com.example.githubtraining.ui.repositories


import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(private val repository: RepositoriesRepository) : ViewModel() {

    var sortNr = repository.sortNrFormDB

    val stuffData = repository.observableDataStuff

    val stuffList=repository.stuffDbList
    val mSuccessReceive = MutableLiveData<Boolean>()
    val mErrorReceive = MutableLiveData<Boolean>()
    val mErrorMsgReceive = ObservableField("")
    val userNameLogged= repository.infoUserLogged

    val repoListLiveData = repository.getDataRepo{ success, error, errorMsg->
        when{
            success-> mSuccessReceive.value=true
            error-> {
                mErrorMsgReceive.set(errorMsg)
                mErrorReceive.value=true
            }
        }
    }

}