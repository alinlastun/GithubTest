package com.example.githubtraining.screen.repositories

import android.util.Log
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.utill.repository.RepositoryRepoDB
import com.example.githubtraining.utill.repository.RepositoryStuffDB
import com.example.githubtraining.utill.repository.RepositoryUserDB
import com.example.githubtraining.utill.repository.RepositoryWs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoriesRepository @Inject constructor(private val mRepositoryRepoDB: RepositoryRepoDB, mRepositoryUserDB: RepositoryUserDB, mRepositoryStuff: RepositoryStuffDB, private val repositoryWS:RepositoryWs) {

    private lateinit var listener: (success: Boolean, error: Boolean,errorMsg:String) -> Unit
    var infoUserLogged = mRepositoryUserDB.getUserLogged()
    var observableDataStuff = mRepositoryStuff.getStuffFromDB()
    var observableDataRepo = mRepositoryRepoDB.getLiveDataInfoRepo()
    var sortNrFormDB = mRepositoryStuff.getSortNr()
    var repoList = mRepositoryRepoDB.getListInfoRepo()
    var  stuffDbList = mRepositoryStuff.getStuffListFromDB()


    fun getRepoData(userPass:String,listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit): Disposable {
        this.listener= listener
        return repositoryWS.getRepoList(userPass)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::successRepoList, this::errorRepoList)
    }

    private fun successRepoList(repoList:MutableList<InfoRepoModelDB>){
        mRepositoryRepoDB.deleteInfoRepo()
        mRepositoryRepoDB.insertInfoRepo(repoList)
        listener.invoke(true,false,"")
    }

    private fun errorRepoList(mError: Throwable){
        Log.d("Asdfasdf",mError.message)
        listener.invoke(false,true,mError.message.toString())

    }

}