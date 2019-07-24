package com.example.githubtraining.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubtraining.database.dao.DaoInfoRepo
import com.example.githubtraining.database.dao.DaoInfoUser
import com.example.githubtraining.database.dao.DaoStuff
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.StuffModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB

@Database(entities = [(UserInformationModelDB::class),(InfoRepoModelDB::class),(StuffModelDB::class)], version =1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun daoInfoUser(): DaoInfoUser
    abstract fun daoInfoRepo(): DaoInfoRepo
    abstract fun daoStuff(): DaoStuff
}
