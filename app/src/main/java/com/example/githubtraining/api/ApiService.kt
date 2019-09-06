package com.example.githubtraining.api

import com.example.githubtraining.api.model.RepositoryInformation
import com.example.githubtraining.api.model.UserInformation
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("/user") suspend fun getUserInfo(@Header("Authorization")userPass: String):
        Response<UserInformation>
    @GET("/user/repos") suspend fun getInfoRepo(@Header("Authorization")userPass: String):
        Response<List<RepositoryInformation>>
}