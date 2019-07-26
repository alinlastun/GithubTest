package com.example.githubtraining.ui.repositories
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubtraining.database.dao.DaoInfoRepo
import com.example.githubtraining.database.dao.DaoInfoUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val repository: RepositoriesRepository,
    private val daoInfoRepo: DaoInfoRepo,
    daoInfoUser: DaoInfoUser
) : ViewModel() {

    val mSuccessReceive = MutableLiveData<Boolean>()
    val mErrorReceive = MutableLiveData<Boolean>()
    val mErrorMsgReceive = ObservableField("")

    var sortNr = repository.sortNrFormDB
    val stuffData = repository.observableDataStuff
    val stuffList = repository.stuffDbList
    val userNameLogged = daoInfoUser.getUserLogged()
    val infoRepoLiveData = daoInfoRepo.getInfoRepo()

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getInfoRepo()
    }

    private fun getInfoRepo() {
        viewModelScope.launch {
             repository.refreshDataRepo { success, error, errorMsg ->
                when {
                    success -> mSuccessReceive.postValue(true)
                    error -> {
                        mErrorMsgReceive.set(errorMsg)
                        mErrorReceive.postValue(true)
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}