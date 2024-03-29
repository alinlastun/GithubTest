package com.example.githubtraining.screen.login

import android.content.Context
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import com.example.githubtraining.model.LoginModelError
import com.example.githubtraining.utill.repository.RepositoryUserDB
import com.example.githubtraining.utill.repository.RepositoryWs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class LoginRepository(private val mViewModel: LoginViewModel, mContext: Context) {
    private val repositoryWS = RepositoryWs()
    private val repositoryDB = RepositoryUserDB(mContext)

    fun login(userPass: String): Disposable {
        return repositoryWS.loginUser(userPass)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::successLogin, this::errorLogin)
    }


    private fun successLogin(userInfo: UserInformationModelDB) {
        userInfo.isLogin=true
        userInfo.encodedUserPass= mViewModel.encodedUserPass
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
            mViewModel.mErrorLogin.value = true
        }

    }


}