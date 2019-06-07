package com.example.githubtraining.utill.repository

import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import com.example.githubtraining.retrofit.RetrofitService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepositoryWs {

    fun loginUser(userPass: String): Observable<UserInformationModelDB> {
        return RetrofitService().getInstance().interfaces.userLogin(userPass)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getRepoList(userPass:String): Observable<MutableList<InfoRepoModelDB>> {
        return RetrofitService().getInstance().interfaces.getRepo(userPass)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}