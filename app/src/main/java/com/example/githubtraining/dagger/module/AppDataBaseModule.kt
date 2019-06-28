package com.example.githubtraining.dagger.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.example.githubtraining.database.AppDataBase
import com.example.githubtraining.database.dao.DaoInfoRepo
import com.example.githubtraining.database.dao.DaoInfoUser
import com.example.githubtraining.database.dao.DaoStuff
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDataBaseModule {

    private lateinit var appDataBase : AppDataBase


    @Singleton @Provides
    fun provideAppDataBase(context: Context):AppDataBase{
        appDataBase =  Room.databaseBuilder(context, AppDataBase::class.java, "demo-db").allowMainThreadQueries().build()
        return appDataBase
    }

    @Singleton @Provides
    fun provideDaoInfoRepo():DaoInfoRepo{
        return appDataBase.daoInfoRepo()
    }

    @Singleton @Provides
    fun provideDaoInfoUser(): DaoInfoUser {
        return appDataBase.daoInfoUser()
    }

    @Singleton @Provides
    fun provideDaoStuff(): DaoStuff {
        return appDataBase.daoStuff()
    }
}