package com.example.githubtraining.ui.repositories

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.githubtraining.R
import com.example.githubtraining.database.dao.DaoInfoRepo
import com.example.githubtraining.database.dao.DaoInfoUser
import com.example.githubtraining.database.dao.DaoStuff
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.model.LoginModelError
import com.example.githubtraining.model.Member
import com.example.githubtraining.retrofit.ServiceUtil
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class RepositoriesRepository @Inject constructor(
    private val serviceUtil: ServiceUtil,
    private val daoInfoRepo: DaoInfoRepo,
    private val daoInfoUser: DaoInfoUser,
    private val daoStuff: DaoStuff,
    val application: Application
) {

    @Inject
    lateinit var pref: SharedPreferences
    private val repoListCollaborator: MutableList<InfoRepoModelDB> = mutableListOf()
    private val repoListOwner: MutableList<InfoRepoModelDB> = mutableListOf()
    val stuffLiveData = daoStuff.getStuff()

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


    fun getRepoList(): LiveData<List<InfoRepoModelDB>> {
        var members = Member()
        daoStuff.getMembers().forEach { members = it }
        return Transformations.map(daoInfoRepo.getRepoListSorted(daoStuff.getSortNumber())) { repoList ->
            getAffiliationRepo(members,repoList)
        }
    }

    private fun getAffiliationRepo(members: Member, repoList: List<InfoRepoModelDB>): List<InfoRepoModelDB> {
        getCollaboratorList(repoList)

        return if (members.collaborator && !members.owner) {
            repoListCollaborator
        } else if (members.owner && !members.collaborator) {
            repoListOwner
        } else if (members.owner && members.collaborator) {
            repoList
        } else {
            repoList
        }
    }

    private fun getCollaboratorList(repoList: List<InfoRepoModelDB>) {
        repoListCollaborator.clear()
        repoListOwner.clear()
        for (value in repoList) {
            value.full_name?.let { fullName ->
                if (!fullName.contains(daoInfoUser.getUserLogged())) {
                    repoListCollaborator.add(value)
                } else if (fullName.contains(daoInfoUser.getUserLogged())) {
                    repoListOwner.add(value)
                } else {
                }
            }
        }
    }
}