package com.example.githubtraining.screen.repoDetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import com.example.githubtraining.database.modelDB.InfoRepoModelDB

class RepoDetailsViewModel: ViewModel() {

    val mNameRepository = ObservableField("")
    val mDescriptionRepository = ObservableField("")
    val mStatusRepository = ObservableField("")


    fun getRepoFromDBById(repoId:Int): LiveData<InfoRepoModelDB> {
        return RepoDetailsRepository.getRepoById(repoId)
    }


}