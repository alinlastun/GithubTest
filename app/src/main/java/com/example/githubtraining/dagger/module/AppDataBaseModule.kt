package com.example.githubtraining.dagger.module
import android.content.Context
import androidx.room.Room
import com.example.githubtraining.database.AppDataBase
import com.example.githubtraining.database.dao.DaoInfoRepo
import com.example.githubtraining.database.dao.DaoInfoUser
import com.example.githubtraining.database.dao.DaoStuff
import com.example.githubtraining.utilities.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDataBaseModule {
    @Singleton @Provides
    fun provideAppDataBase(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME).allowMainThreadQueries().build()
    }

    @Singleton @Provides
    fun provideDaoInfoRepo(db: AppDataBase): DaoInfoRepo {
        return db.daoInfoRepo()
    }

    @Singleton @Provides
    fun provideDaoInfoUser(db: AppDataBase): DaoInfoUser {
        return db.daoInfoUser()
    }

    @Singleton @Provides
    fun provideDaoStuff(db: AppDataBase): DaoStuff {
        return db.daoStuff()
    }
}