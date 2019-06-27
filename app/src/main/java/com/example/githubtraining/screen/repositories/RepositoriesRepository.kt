package com.example.githubtraining.screen.repositories

import android.content.Context
import android.util.Log
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.screen.repoDetails.RepoDetailsRepository
import com.example.githubtraining.utill.repository.RepositoryRepoDB
import com.example.githubtraining.utill.repository.RepositoryStuffDB
import com.example.githubtraining.utill.repository.RepositoryUserDB
import com.example.githubtraining.utill.repository.RepositoryWs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoriesRepository @Inject constructor(private val mRepositoryRepoDB: RepositoryRepoDB,private val mRepositoryUserDB: RepositoryUserDB,private val mRepositoryStuff: RepositoryStuffDB) {

    private val mRepositoryWS = RepositoryWs()

    var mEncodedUserPass:String = mRepositoryUserDB.getEncodedUserPass()
    var observableDataStuff = mRepositoryStuff.getStuffFromDB()
    var observableDataRepo = mRepositoryRepoDB.getLiveDataInfoRepo()
    var sortNrFormDB = mRepositoryStuff.getSortNr()


    fun getRepoData(userPass:String): Disposable {
        return mRepositoryWS.getRepoList(userPass)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::successRepoList, this::errorRepoList)
    }

    private fun successRepoList(repoList:MutableList<InfoRepoModelDB>){
        mRepositoryRepoDB.deleteInfoRepo()
        mRepositoryRepoDB.insertInfoRepo(repoList)
    }

    private fun errorRepoList(mError: Throwable){
        Log.d("Asdfasdf",mError.message)
    }
}