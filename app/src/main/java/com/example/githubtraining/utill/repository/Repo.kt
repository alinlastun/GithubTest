package com.example.githubtraining.utill.repository

import androidx.lifecycle.LiveData
import com.example.githubtraining.database.modelDB.InfoRepoModelDB

interface Repo {
    fun getLiveDataInfoRepo(listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit) : LiveData<MutableList<InfoRepoModelDB>>
    fun getListInfoRepo() : MutableList<InfoRepoModelDB>
    fun insertInfoRepo(repoList: List<InfoRepoModelDB>)
    fun getInfoRepoById(repoId:Int):LiveData<InfoRepoModelDB>
    fun deleteInfoRepo()
}