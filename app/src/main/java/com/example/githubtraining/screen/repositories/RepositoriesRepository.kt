package com.example.githubtraining.screen.repositories

import android.content.Context
import android.util.Log
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.utill.repository.RepositoryRepoDB
import com.example.githubtraining.utill.repository.RepositoryStuffDB
import com.example.githubtraining.utill.repository.RepositoryUserDB
import com.example.githubtraining.utill.repository.RepositoryWs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RepositoriesRepository(mContext: Context) {

    private val mRepositoryWS = RepositoryWs()
    private val mRepositoryUserDB = RepositoryUserDB(mContext)
    private val mRepositoryRepoDB = RepositoryRepoDB(mContext)
    private val mRepositoryStuff = RepositoryStuffDB(mContext)

    var mEncodedUserPass:String = mRepositoryUserDB.getEncodedUserPass()
    var observableDataStuff = mRepositoryStuff.getStuffFromDB()
    var observableDataRepo = mRepositoryRepoDB.getInfoRepoFromDB()
    var sortNrFormDB = mRepositoryStuff.getSortNr()


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