package com.example.githubtraining.screen.repositories

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

    var infoUserLogged = mRepositoryUserDB.getUserLogged()
    var observableDataStuff = mRepositoryStuff.getStuffFromDB()
    var observableDataRepo = mRepositoryRepoDB.getLiveDataInfoRepo()
    var sortNrFormDB = mRepositoryStuff.getSortNr()
    var  stuffDbList = mRepositoryStuff.getStuffListFromDB()


    fun getRepoData(listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit): Disposable {
        return repositoryWS.getRepoList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({successRepoList(it,listener)},{errorRepoList(it,listener)})

    }

    private fun successRepoList(repoList:MutableList<InfoRepoModelDB>,listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit){
        mRepositoryRepoDB.deleteInfoRepo()
        mRepositoryRepoDB.insertInfoRepo(repoList)
        listener.invoke(true,false,"")
    }

    private fun errorRepoList(mError: Throwable,listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit){
        listener.invoke(false,true,mError.message.toString())

    }

}