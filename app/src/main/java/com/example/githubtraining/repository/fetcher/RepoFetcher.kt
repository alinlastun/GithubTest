package com.example.githubtraining.repository.fetcher

import android.app.Application
import android.content.SharedPreferences
import com.example.githubtraining.R
import com.example.githubtraining.api.ApiService
import com.example.githubtraining.db.dao.DaoInfoRepo
import com.example.githubtraining.repository.apimapper.RepoInfoMapper
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class RepoFetcher  @Inject constructor(
    private val service: ApiService,
    private val daoInfoRepo: DaoInfoRepo,
    private val application: Application,
    private val pref: SharedPreferences
)  {

    /**
     * Fetch vehicle makes from API while starting a new coroutine on the IO dispatcher
     */

    suspend fun fetchRepoInfo(listener: (isSuccess: Boolean, errorMsg: String) -> Unit) {
        try {
            val userCredential = pref.getString(
                application.getString(R.string.sharedPrefToken),
                application.getString(R.string.sharedPrefNoToken)
            )
            val response = userCredential?.let { service.getInfoRepo(it) }
            if (response != null) {
                if (response.isSuccessful) {
                    response.body()?.let { repoList ->
                        daoInfoRepo.upsert(repoList.map { RepoInfoMapper.map(it) })
                    }
                    listener.invoke(true, "")
                } else {
                    listener.invoke(false, response.errorBody().toString())
                }
            } else {
            }
        } catch (exception: IOException) {
            Timber.e(exception)
        }
    }
}