package com.example.githubtraining.utill.repository

import androidx.lifecycle.LiveData
import com.example.githubtraining.database.modelDB.StuffModelDB

interface Stuff {
    fun getStuffFromDB(): LiveData<StuffModelDB>
    fun getStuffListFromDB(): MutableList<StuffModelDB>
    fun insertStuffIntoDB(stuffDB: StuffModelDB)
    fun deleteStuff()
    fun getSortNr(): Int
    fun getRadioButtonId(): Int
    fun upDateSort(sortItem: Int)

}