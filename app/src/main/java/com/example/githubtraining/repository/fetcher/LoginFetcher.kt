package com.example.githubtraining.repository.fetcher

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import com.example.githubtraining.R
import com.example.githubtraining.api.ApiService
import com.example.githubtraining.db.dao.DaoInfoUser
import com.example.githubtraining.repository.apimapper.LoginMapper
import extensions.CD
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class LoginFetcher @Inject constructor(
    private val service: ApiService,
    private val daoUserInfo: DaoInfoUser,
    private val application: Application,
    private val pref: SharedPreferences
) {

    /**
     * Fetch vehicle makes from API while starting a new coroutine on the IO dispatcher
     */

    suspend fun fetchUserInfo(listener: (isSuccess: Boolean, errorMsg: String) -> Unit) {
        Log.d("TimberExtensionsKt", "fetchUserInfo")
        Log.d("", "fetchUserInfo 2")
        try {
            val userCredential = pref.getString(
                application.getString(R.string.sharedPrefToken),
                application.getString(R.string.sharedPrefNoToken)
            )
            Log.d("TimberExtensionsKt", "fetchUserInfo 3 : " + userCredential)
            val response = userCredential?.let { service.getUserInfo(it) }
            if (response != null) {
                if (response.isSuccessful) {
                    Log.d("TimberExtensionsKt", "isSuccessful")
                    CD("Success fetching campaign from network ")
                    response.body()?.let {
                        daoUserInfo.upsert(LoginMapper.map(it))
                        Log.d("TimberExtensionsKt", "login: ${LoginMapper.map(it).avatarUrl}")
                    }
                    listener.invoke(true, "")
                } else {
                    Log.d("TimberExtensionsKt", "failed ${ response.message()}")
                    CD("Error fetching campaign from network : ${response.errorBody()}")
                    listener.invoke(false, response.errorBody().toString())
                }
            } else {
                Log.d("TimberExtensionsKt", "response null")
                CD("Error fetching campaign from network : response null")
            }
        } catch (exception: IOException) {
            Log.d("TimberExtensionsKt", "IOException: " + exception.message)
            Timber.e(exception)
        }
    }
}