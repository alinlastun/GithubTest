package com.example.githubtraining.retrofit

import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.model.NetworkInfoUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ServiceUtil {
    @GET("/user") suspend fun userLoginAsync(@Header("Authorization")userPass: String):
        Response<NetworkInfoUser>
    @GET("/user/repos") suspend fun getRepoAsync(@Header("Authorization")userPass: String):
        Response<MutableList<InfoRepoModelDB>>
}