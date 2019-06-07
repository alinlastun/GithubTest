package com.example.githubtraining.database

import android.arch.persistence.room.TypeConverter
import com.example.githubtraining.model.RepoModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class GithubTypeConverters {
    var gson = Gson()

    @TypeConverter
    fun stringToItemItemList(data: String?): List<RepoModel> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<RepoModel>>() {

        }.type

        return gson.fromJson<List<RepoModel>>(data, listType)
    }

    @TypeConverter
    fun ItemInfoListToString(someObjects: List<RepoModel>): String {
        return gson.toJson(someObjects)
    }


}