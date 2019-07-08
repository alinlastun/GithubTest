package com.example.githubtraining.utill.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.githubtraining.R
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import com.example.githubtraining.retrofit.ServiceUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryWs @Inject constructor(private val serviceUtil: ServiceUtil){

    @Inject lateinit var pref: SharedPreferences
    @Inject lateinit var context: Context

    fun loginUser(): Observable<UserInformationModelDB> {
        return serviceUtil.userLogin(pref.getString(context.getString(R.string.sharedPrefToken),context.getString(R.string.sharedPrefNoToken))!!)
            .subscribeOn(Schedulers.io())
    }

    fun getRepoList(): Observable<MutableList<InfoRepoModelDB>> {
        return serviceUtil.getRepo(pref.getString(context.getString(R.string.sharedPrefToken), context.getString(R.string.sharedPrefNoToken))!!)
            .subscribeOn(Schedulers.io())
    }

}