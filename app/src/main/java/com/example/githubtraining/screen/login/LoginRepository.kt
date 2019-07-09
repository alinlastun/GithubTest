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

class LoginRepository  @Inject constructor (private val repositoryDB : RepositoryUserDB, private val repositoryWS:RepositoryWs ) {

    fun getDataUser(listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit): LiveData<MutableList<UserInformationModelDB>> {
      return repositoryDB.getInfoUserFromDB(listener)
    }

    /*fun login(listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit): Disposable {
        return repositoryWS.loginUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({successLogin(it,listener)}, {errorLogin(it,listener)})
    }

    private fun successLogin(userInfo: UserInformationModelDB,listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit) {
        listener.invoke(true,false,"")
        repositoryDB.insertInfoUserIntoDB(userInfo)
    }

    private fun errorLogin(mError: Throwable,listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit) {
        if (mError is HttpException) {
            val mErrorModel =
                com.google.gson.Gson().fromJson(mError.response().errorBody()!!.string(), LoginModelError::class.java)
            if (mError.code() == 401) {
                listener.invoke(false,true,mErrorModel.message)
            }
        } else if (mError.message?.contains("No address associated with hostname")!!) {
            listener.invoke(false,true,"No Internet Connection!!")
        }

    }*/


}