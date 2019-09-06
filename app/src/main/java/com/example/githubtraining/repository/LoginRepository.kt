package com.example.githubtraining.repository

import com.example.githubtraining.db.dao.DaoInfoUser
import com.example.githubtraining.domain.mapper.LoginMapper
import com.example.githubtraining.domain.model.UserInformation
import com.example.githubtraining.repository.fetcher.LoginFetcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginFetcher: LoginFetcher,
    private val daoUserInfo: DaoInfoUser) {

    /**
     * Get a Flow with the DB data but also trigger a fetch from the network.
     * All concurrent operations will be executed in the [scope] CoroutineScope.
     */
     fun getUserInfo(scope: CoroutineScope,listener: (isSuccess: Boolean, errorMsg: String) -> Unit): Flow<UserInformation>{
        scope.launch {
            loginFetcher.fetchUserInfo(listener)

        }
        return daoUserInfo.getAllInfoUser().map {dbUserInfo ->
            LoginMapper.map(dbUserInfo)
        }
    }
}