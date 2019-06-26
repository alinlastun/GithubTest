package com.example.githubtraining.utill.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.AppDataBase
import com.example.githubtraining.database.modelDB.StuffModelDB
import com.example.githubtraining.database.modelDB.UserInformationModelDB
import javax.inject.Inject

open class RepositoryStuffDB(mContext: Context) {
    @Inject
    lateinit var appDB:AppDataBase

    init {
        appComponent.inject(this)
    }


   open fun getStuffFromDB() : LiveData<StuffModelDB> {
        return appDB.daoStuff().getStuff()
    }

    open fun insertStuffIntoDB(stuffDB: StuffModelDB) {
        AddAsynTask(appDB).execute(stuffDB)
    }

    fun deleteStuff(){
        appDB.daoStuff().deleteStuff()
    }

    open  fun getSortNr():Int{
        return appDB.daoStuff().getSortNumber()
    }

    open fun getRadioButtonId():Int{
        return appDB.daoStuff().getRadioBtnId()
    }

    open fun upDateSort(sortItem:Int){
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