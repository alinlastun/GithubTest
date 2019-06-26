package com.example.githubtraining.utill.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import com.example.githubtraining.database.AppDataBase
import com.example.githubtraining.database.modelDB.StuffModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB

class RepositoryStuffDB(mContext: Context) {
    private var appDB: AppDataBase = AppDataBase.getDataBase(mContext)

    fun getStuffFromDB() : LiveData<StuffModelDB> {
        return appDB.daoStuff().getStuff()
    }

    fun insertStuffIntoDB(stuffDB: StuffModelDB) {
        AddAsynTask(appDB).execute(stuffDB)
    }

    fun deleteStuff(){
        appDB.daoStuff().deleteStuff()
    }

    fun getSortNr():Int{
        return appDB.daoStuff().getSortNumber()
    }

    fun getRadioButtonId():Int{
        return appDB.daoStuff().getRadioBtnId()
    }

    fun upDateSort(sortItem:Int){
        appDB.daoStuff().updateSort(sortItem)
    }

    class AddAsynTask(db: AppDataBase) : AsyncTask<StuffModelDB, Void, Void>() {
        private var infoUserDB = db
        override fun doInBackground(vararg params: StuffModelDB): Void? {
            infoUserDB.daoStuff().insertStuff(params[0])
            return null
        }
    }
}