package com.example.githubtraining.screen.repoDetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import com.example.githubtraining.database.modelDB.InfoRepoModelDB

class RepoDetailsViewModel(mContext: Context): ViewModel() {
    val mNameRepository = ObservableField("")
    val mDescriptionRepository = ObservableField("")
    val mStatusRepository = ObservableField("")
    val repository = RepoDetailsRepository(mContext)


    fun getRepoFromDBById(repoId:Int): LiveData<InfoRepoModelDB> {
        return repository.getRepoById(repoId)
    }


}