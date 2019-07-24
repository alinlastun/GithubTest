package com.example.githubtraining.utill.repository

import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread
import com.example.githubtraining.database.dao.DaoInfoUser
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import com.example.githubtraining.model.LoginModelError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Named

class RepositoryUserDB @Inject constructor(private val daoInfoUser:DaoInfoUser,private val repositoryWS:RepositoryWs,@Named("DiskExecutor") private val  diskExecutor : Executor):User {


    override fun getInfoUserFromDB(listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit) : LiveData<MutableList<UserInformationModelDB>> {
        getDataUser(listener)
        return daoInfoUser.getInfoUser()
    }

    override fun getListOfInfoUser():MutableList<UserInformationModelDB>{
        return daoInfoUser.getListOfInfoUser()
    }

    @WorkerThread
    override fun insertInfoUserIntoDB(movieDB: UserInformationModelDB) {
        diskExecutor.execute { daoInfoUser.insertInfoUser(movieDB) }
    }

    override fun deleteInfoUser(){
        daoInfoUser.deleteInfoUser()
    }

    override fun getUserLogged():String{
        return daoInfoUser.getUserLogged()
    }


    private fun getDataUser(listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit): Disposable {
        return repositoryWS.loginUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({successLogin(it,listener)}, {errorLogin(it,listener)})
    }

    private fun successLogin(userInfo: UserInformationModelDB,listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit) {
        listener.invoke(true,false,"")
        insertInfoUserIntoDB(userInfo)
    }

    private fun errorLogin(mError: Throwable,listener: (success:Boolean, error:Boolean,errorMsg:String) -> Unit) {
        if (mError is HttpException) {
            val mErrorModel =
                com.google.gson.Gson().fromJson(mError.response().errorBody()!!.string(), LoginModelError::class.java)
            if (mError.code() == 401) {
                listener.invoke(false,true,mErrorModel.message)
            }
        } else if (mError.message?.contains("No address associated with hostname")!!) {
            listener.invoke(false,true,"No Internet Connection!!")
        }

    }

}