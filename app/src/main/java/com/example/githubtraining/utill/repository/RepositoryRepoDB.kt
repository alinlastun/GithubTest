package com.example.githubtraining.utill.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.util.Log.d
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.AppDataBase
import com.example.githubtraining.database.dao.DaoInfoRepo
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import com.example.githubtraining.screen.repositories.RepositoriesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Named
import android.R.string
import java.lang.Exception
import java.text.DateFormat
import java.util.*
import kotlin.collections.HashMap




class RepositoryRepoDB @Inject constructor(private val daoInfoRepo:DaoInfoRepo, private val repositoryWS:RepositoryWs, @Named("DiskExecutor") private val  diskExecutor : Executor) :Repo{


    override fun getInfoRepoById(repoId: Int): LiveData<InfoRepoModelDB> {
        return daoInfoRepo.getRepoById(repoId)
    }

    override fun deleteInfoRepo() {
        daoInfoRepo.deleteInfoRepo()
    }

    override fun getLiveDataInfoRepo(listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit): LiveData<MutableList<InfoRepoModelDB>> {
        // refresh from network
        getRepoData(listener)
        return daoInfoRepo.getInfoRepo()
    }

    override fun getListInfoRepo(): MutableList<InfoRepoModelDB> {
        return daoInfoRepo.getInfoRepoList()
    }

    override fun insertInfoRepo(repoList: List<InfoRepoModelDB>) {
        diskExecutor.execute {  daoInfoRepo.insertInfoRepo(repoList) }
    }

    private fun getRepoData(listener: (success:Boolean, error:Boolean, errorMsg:String) -> Unit): Disposable {
        return repositoryWS.getRepoList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({successRepoList(it,listener)},{errorRepoList(it,listener)})

    }



    private fun successRepoList(repoList:MutableList<InfoRepoModelDB>,listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit){
        for (list in repoList){
            d("af4aw23","${list.name} ${list.type}")
        }
        val myRepoList: MutableList<InfoRepoModelDB>  = repoList
        val myList: MutableList<InfoRepoModelDB> = repoList.sortedWith(compareByDescending<InfoRepoModelDB> { it.created_at }.thenByDescending { it.created_at }) as MutableList<InfoRepoModelDB>
        val myListOfString: MutableList<String>  = arrayListOf()

        for (list in myList) {
            val parts = list.created_at!!.substring(0, 4)
            myListOfString.add(parts)
        }

        var lastYearsaved=""
        for (i in 0 .. myListOfString.size){
                if(i<myListOfString.size-1 ){
                    if(myListOfString[i]>myListOfString[i+1] ){
                        val model = InfoRepoModelDB()
                        model.created_at=myListOfString[i]
                        model.id=myListOfString[i].toInt()
                        model.id_table=myListOfString[i].toInt()
                        model.type=2
                        model.full_name=myListOfString[i]
                        model.name=myListOfString[i]
                        model.updated_at=myListOfString[i]
                        model.private=false
                        model.description=myListOfString[i]
                        model.pushed_at=myListOfString[i]
                        myRepoList.add(model)
                        lastYearsaved = myListOfString[i]
                    }
                }
        }
        if(myListOfString[ myListOfString.size-1]<lastYearsaved ){
            val model2 = InfoRepoModelDB()
            model2.created_at=myListOfString[ myListOfString.size-1]
            model2.id=myListOfString[ myListOfString.size-1].toInt()
            model2.id_table=myListOfString[ myListOfString.size-1].toInt()
            model2.type=2
            model2.full_name=myListOfString[ myListOfString.size-1]
            model2.name=myListOfString[ myListOfString.size-1]
            model2.updated_at=myListOfString[ myListOfString.size-1]
            model2.private=false
            model2.description=myListOfString[ myListOfString.size-1]
            model2.pushed_at=myListOfString[ myListOfString.size-1]
           // model2.type=2
           // model2.name=myListOfString[ myListOfString.size-1]
            myRepoList.add(model2)
            d("3rfadsdaa","else")
        }

        d("3rfadsdaa","${myRepoList.size}")
        for (list in myRepoList){
            d("3rfadsdaa","${list.name} ${list.type}")
        }
        deleteInfoRepo()
        insertInfoRepo(myRepoList)
        d("r342fafsf","${myRepoList.size}")
        listener.invoke(true,false,"")
    }

    private fun errorRepoList(mError: Throwable,listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit){
        listener.invoke(false,true,mError.message.toString())

    }

}