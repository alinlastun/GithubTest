package com.example.githubtraining.screen.login

import android.arch.lifecycle.LiveData
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import com.example.githubtraining.model.LoginModelError
import com.example.githubtraining.utill.repository.RepositoryUserDB
import com.example.githubtraining.utill.repository.RepositoryWs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import javax.inject.Inject

class LoginRepository  @Inject constructor (private val repositoryDB : RepositoryUserDB ) {

    fun getDataUser(listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit): LiveData<MutableList<UserInformationModelDB>> {
        return repositoryDB.getInfoUserFromDB(listener)
    }

}