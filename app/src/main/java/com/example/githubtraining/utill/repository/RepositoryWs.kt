package com.example.githubtraining.utill.repository

import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import com.example.githubtraining.retrofit.ServiceUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryWs @Inject constructor(private val serviceUtil: ServiceUtil){

    fun loginUser(userPass: String): Observable<UserInformationModelDB> {
        return serviceUtil.userLogin(userPass)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getRepoList(userPass:String): Observable<MutableList<InfoRepoModelDB>> {
        return serviceUtil.getRepo(userPass)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}