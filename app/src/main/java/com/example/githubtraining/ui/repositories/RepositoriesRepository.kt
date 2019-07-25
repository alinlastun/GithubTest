package com.example.githubtraining.ui.repositories

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import com.example.githubtraining.R
import com.example.githubtraining.database.dao.DaoInfoRepo
import com.example.githubtraining.database.dao.DaoStuff
import com.example.githubtraining.model.LoginModelError
import com.example.githubtraining.retrofit.ServiceUtil
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class RepositoriesRepository @Inject constructor(
    private val serviceUtil: ServiceUtil,
    private val daoInfoRepo: DaoInfoRepo,
    private val daoStuff: DaoStuff,
    val application: Application
) {

    @Inject
    lateinit var pref: SharedPreferences
    var observableDataStuff = daoStuff.getStuff()
    var sortNrFormDB = daoStuff.getSortNumber()
    var stuffDbList = daoStuff.getStuffList()


    suspend fun refreshDataRepo(listener: (success: Boolean, error: Boolean, errorMsg: String) -> Unit) {
        withContext(Dispatchers.IO) {
            val response = serviceUtil.getRepoAsync(
                pref.getString(
                    application.getString(R.string.sharedPrefToken),
                    application.getString(R.string.sharedPrefNoToken)
                )!!
            ).await()

            if (response.isSuccessful) {
                Log.d("asdfasd", "isSuccessful")
                listener.invoke(true, false, "")
                daoInfoRepo.deleteInfoRepo()
                response.body()?.let { daoInfoRepo.insertInfoRepo(it) }
            } else {
                Log.d("asdfasd", "error")
                val jObjError = JSONObject(response.errorBody()?.string())
                val mErrorModel = Gson().fromJson(jObjError.toString(), LoginModelError::class.java)

                when {
                    response.code() == 401 -> listener.invoke(false, true, mErrorModel.message)
                    response.message().contains("No address associated with hostname") -> listener.invoke(
                        false,
                        true,
                        "No Internet Connection!!"
                    )
                    else -> {
                    }
                }
            }
        }
    }


}