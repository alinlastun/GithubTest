package com.example.githubtraining.ui.login

import androidx.lifecycle.LiveData
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import com.example.githubtraining.utill.repository.RepositoryUserDB
import javax.inject.Inject

class LoginRepository  @Inject constructor (private val repositoryDB : RepositoryUserDB ) {

    fun getDataUser(listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit): LiveData<MutableList<UserInformationModelDB>> {
        return repositoryDB.getInfoUserFromDB(listener)
    }

}