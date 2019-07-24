package com.example.githubtraining.ui.login

import android.app.Application
import android.content.SharedPreferences
import com.example.githubtraining.R
import com.example.githubtraining.database.dao.DaoInfoUser
import com.example.githubtraining.model.LoginModelError
import com.example.githubtraining.retrofit.ServiceUtil
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject


class LoginRepository  @Inject constructor (private val daoInfoUser: DaoInfoUser,private val serviceUtil: ServiceUtil,val application: Application) {

    @Inject lateinit var pref: SharedPreferences

    suspend fun refreshDataUser(listener: (success:Boolean, error:Boolean, errorMsg:String) -> Unit) {

        withContext(Dispatchers.IO) {

            val response = serviceUtil.userLoginAsync(
                pref.getString(
                    application.getString(R.string.sharedPrefToken),
                    application.getString(R.string.sharedPrefNoToken)
                )!!
            ).await()
            try {
                if (response.isSuccessful) {
                    listener.invoke(true,false,"")
                    response.body()?.let { daoInfoUser.insertInfoUser(it) }
                } else {

                    val jObjError = JSONObject(response.errorBody()?.string())
                    val mErrorModel = Gson().fromJson(jObjError.toString(), LoginModelError::class.java)

                    when {
                        response.code() == 401 -> listener.invoke(false,true,mErrorModel.message)
                        response.message().contains("No address associated with hostname") -> listener.invoke(false,true,"No Internet Connection!!")
                        else -> { }
                    }

                }
            } catch (e: HttpException) {
                listener.invoke(false,true,response.message())
            } catch (e: Throwable) {
                listener.invoke(false,true,response.message())
            }
        }

    }
}