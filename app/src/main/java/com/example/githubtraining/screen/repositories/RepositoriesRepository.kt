package com.example.githubtraining.screen.repositories

import android.arch.lifecycle.LiveData
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.utill.repository.RepositoryRepoDB
import com.example.githubtraining.utill.repository.RepositoryStuffDB
import com.example.githubtraining.utill.repository.RepositoryUserDB
import javax.inject.Inject

class RepositoriesRepository @Inject constructor(private val mRepositoryRepoDB: RepositoryRepoDB, mRepositoryUserDB: RepositoryUserDB, mRepositoryStuff: RepositoryStuffDB) {

    var infoUserLogged = mRepositoryUserDB.getUserLogged()
    var observableDataStuff = mRepositoryStuff.getStuffFromDB()
    var sortNrFormDB = mRepositoryStuff.getSortNr()
    var  stuffDbList = mRepositoryStuff.getStuffListFromDB()


    fun getDataRepo(listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit): LiveData<MutableList<InfoRepoModelDB>> {
        return mRepositoryRepoDB.getLiveDataInfoRepo(listener)
    }


}