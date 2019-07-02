package com.example.githubtraining.screen.login

import com.example.githubtraining.database.modelDB.UserInformationModelDB
import com.example.githubtraining.model.LoginModelError
import com.example.githubtraining.utill.repository.RepositoryUserDB
import com.example.githubtraining.utill.repository.RepositoryWs
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import javax.inject.Inject

class LoginRepository  @Inject constructor (private val repositoryDB : RepositoryUserDB, private val repositoryWS:RepositoryWs ) {

    lateinit var mViewModel:LoginViewModel

    fun login(userPass: String,mViewModel:LoginViewModel): Disposable {
        this.mViewModel=mViewModel
        return repositoryWS.loginUser(userPass)
            .subscribe(this::successLogin, this::errorLogin)
    }


    private fun successLogin(userInfo: UserInformationModelDB) {
        repositoryDB.insertInfoUserIntoDB(userInfo)
        mViewModel.mSuccessLogin.value = true
    }

    private fun errorLogin(mError: Throwable) {

        if (mError is HttpException) {
            val mErrorModel =
                com.google.gson.Gson().fromJson(mError.response().errorBody()!!.string(), LoginModelError::class.java)
            if (mError.code() == 401) {
                mViewModel.mCredentialError.set(mErrorModel.message)
                mViewModel.mErrorLogin.value = true
            }
        } else if (mError.message?.contains("No address associated with hostname")!!) {
            mViewModel.mCredentialError.set("No Internet Connection!!")
            mViewModel.mErrorLogin.value = true
        }

    }


}