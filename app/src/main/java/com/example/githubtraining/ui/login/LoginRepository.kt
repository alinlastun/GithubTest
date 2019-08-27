package com.example.githubtraining.ui.login

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import com.example.githubtraining.R
import com.example.githubtraining.database.dao.DaoInfoUser
import com.example.githubtraining.model.LoginModelError
import com.example.githubtraining.model.asInfoUserDBModel
import com.example.githubtraining.retrofit.ServiceUtil
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val daoInfoUser: DaoInfoUser,
    private val serviceUtil: ServiceUtil,
    private val application: Application
) {

    @Inject
    lateinit var pref: SharedPreferences

    suspend fun refreshDataUser(listener: (success: Boolean, error: Boolean, errorMsg: String) -> Unit) {

            withContext(Dispatchers.IO) {
                val response = try {
                    Log.d("Asdf","" +pref.getString(application.getString(R.string.sharedPrefToken),application.getString(R.string.sharedPrefNoToken)) )
                    serviceUtil.userLoginAsync(
                          pref.getString(application.getString(R.string.sharedPrefToken),application.getString(R.string.sharedPrefNoToken))
                    )
                } catch (e: Exception) {
                    Log.d(TAG, "exception: $e")
                    listener(false, true, "")
                    return@withContext
                }

                if (response.isSuccessful) {
                    listener.invoke(
                        true,
                        false,
                        ""
                    )

                    response.body()?.let {
                        daoInfoUser.deleteInfoUser()
                        daoInfoUser.insertInfoUser(it.asInfoUserDBModel())
                    }
                } else {
                    val jObjError = JSONObject(response.errorBody()?.string())
                    val mErrorModel =
                        Gson().fromJson(jObjError.toString(), LoginModelError::class.java)

                    when {
                        response.code() == 401 -> listener.invoke(
                            false,
                            true,
                            mErrorModel.message
                        )
                        else -> {
                            listener.invoke(
                                false,
                                true,
                                "No Internet Connection!!"
                            )
                        }
                    }
                }

            }
    }

    companion object {
        private val TAG = "LoginRepository"
    }
}