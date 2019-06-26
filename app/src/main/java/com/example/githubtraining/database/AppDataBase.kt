package com.example.githubtraining.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.githubtraining.database.dao.DaoInfoRepo
import com.example.githubtraining.database.dao.DaoInfoUser
import com.example.githubtraining.database.dao.DaoStuff
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.StuffModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB

@Database(entities = [(UserInformationModelDB::class),(InfoRepoModelDB::class),(StuffModelDB::class)], version =1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        private var INSTANCE: AppDataBase? = null
        fun getDataBase(context: Context): AppDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "data-db")
                    .allowMainThreadQueries().build()
            }
            return INSTANCE as AppDataBase
        }
    }

    abstract fun daoInfoUser(): DaoInfoUser
    abstract fun daoInfoRepo(): DaoInfoRepo
    abstract fun daoStuff(): DaoStuff
}
