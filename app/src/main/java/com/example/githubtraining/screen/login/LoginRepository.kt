package com.example.githubtraining.screen.login

import android.location.Location
import android.util.Log
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import com.example.githubtraining.model.LoginModelError
import com.example.githubtraining.utill.repository.RepositoryUserDB
import com.example.githubtraining.utill.repository.RepositoryWs
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import javax.inject.Inject

class LoginRepository  @Inject constructor (private val repositoryDB : RepositoryUserDB, private val repositoryWS:RepositoryWs ) {


    private lateinit var listener: (success: Boolean, error: Boolean,errorMsg:String) -> Unit

    fun login(userPass: String, listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit): Disposable {
        this.listener = listener
        return repositoryWS.loginUser(userPass)
            .subscribe(this::successLogin, this::errorLogin)
    }

    private fun successLogin(userInfo: UserInformationModelDB) {
        listener.invoke(true,false,"")
        repositoryDB.insertInfoUserIntoDB(userInfo)
        Log.d("Asdfasdf","successLogin")
    }

    private fun errorLogin(mError: Throwable) {
        Log.d("Asdfasdf","errorLogin")
        if (mError is HttpException) {
            val mErrorModel =
                com.google.gson.Gson().fromJson(mError.response().errorBody()!!.string(), LoginModelError::class.java)
            if (mError.code() == 401) {
                listener.invoke(false,true,mErrorModel.message)
               // mViewModel.mCredentialError.set(mErrorModel.message)
               // mViewModel.mErrorLogin.value = true
            }
        } else if (mError.message?.contains("No address associated with hostname")!!) {
            listener.invoke(false,true,"No Internet Connection!!")
          //  mViewModel.mCredentialError.set("No Internet Connection!!")
           // mViewModel.mErrorLogin.value = true
        }

    }


}