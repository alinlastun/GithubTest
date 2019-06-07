package com.example.githubtraining.screen.repositories

import android.content.Context
import android.util.Log
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.utill.repository.RepositoryRepoDB
import com.example.githubtraining.utill.repository.RepositoryWs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RepositoriesRepository(mContext: Context) {

    private val mRepositoryWS = RepositoryWs()
    val mRepositoryRepoDB = RepositoryRepoDB(mContext)

    fun getRepoData(userPass:String): Disposable {
        return mRepositoryWS.getRepoList(userPass)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::successRepoList, this::errorRepoList)
    }

    private fun successRepoList(repoList:MutableList<InfoRepoModelDB>){
        mRepositoryRepoDB.deleteInfoRepo()
        mRepositoryRepoDB.insertInfoRepoIntoDB(repoList)
    }

    private fun errorRepoList(mError: Throwable){
        Log.d("Asdfasdf",mError.message)
    }
}