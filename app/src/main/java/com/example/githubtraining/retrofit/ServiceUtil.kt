package com.example.githubtraining.retrofit

import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import com.example.githubtraining.model.NetworkInfoUser
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ServiceUtil {
    @GET("/user") fun userLoginAsync(@Header("Authorization")userPass: String): Deferred<Response<NetworkInfoUser>>
    @GET("/user/repos") fun getRepoAsync(@Header("Authorization")userPass: String): Deferred<Response<MutableList<InfoRepoModelDB>>>
}