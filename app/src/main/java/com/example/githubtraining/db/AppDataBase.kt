package com.example.githubtraining.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubtraining.db.dao.DaoInfoRepo
import com.example.githubtraining.db.dao.DaoInfoUser
import com.example.githubtraining.db.dao.DaoStuff
import com.example.githubtraining.db.model.InfoRepoModelDB
import com.example.githubtraining.db.model.StuffModelDB
import com.example.githubtraining.db.model.UserInformation

@Database(entities = [
    (UserInformation::class),
    (InfoRepoModelDB::class),
    (StuffModelDB::class)], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun daoInfoUser(): DaoInfoUser
    abstract fun daoInfoRepo(): DaoInfoRepo
    abstract fun daoStuff(): DaoStuff
}
