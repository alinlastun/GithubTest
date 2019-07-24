package com.example.githubtraining.utill.repository

import androidx.lifecycle.LiveData
import com.example.githubtraining.database.dao.DaoInfoRepo
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Named


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
        deleteInfoRepo()
        insertInfoRepo(repoList)
        listener.invoke(true,false,"")
    }

    private fun errorRepoList(mError: Throwable,listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit){
        listener.invoke(false,true,mError.message.toString())

    }

}