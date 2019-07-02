package com.example.githubtraining.retrofit

import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceUtil {
    @GET("/user") fun userLogin(@Header("Authorization")userPass: String): Observable<UserInformationModelDB>
    @GET("/user/repos") fun getRepo(@Header("Authorization")userPass: String): Observable<MutableList<InfoRepoModelDB>>
}