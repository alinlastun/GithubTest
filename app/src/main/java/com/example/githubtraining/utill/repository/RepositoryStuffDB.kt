package com.example.githubtraining.utill.repository

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.githubtraining.database.dao.DaoStuff
import com.example.githubtraining.database.modelDB.StuffModelDB
import javax.inject.Inject

open class RepositoryStuffDB @Inject constructor(private val daoStuff: DaoStuff):Stuff {

    override fun getStuffFromDB(): LiveData<StuffModelDB> {
        return daoStuff.getStuff()
    }

    override fun getStuffListFromDB(): MutableList<StuffModelDB> {
        return daoStuff.getStuffList()
    }

    override fun insertStuffIntoDB(stuffDB: StuffModelDB) {
        AddAsynTask(daoStuff).execute(stuffDB)
    }

    override fun deleteStuff() {
        return daoStuff.deleteStuff()
    }

    override fun getSortNr(): Int {
        return daoStuff.getSortNumber()
    }

    override fun getRadioButtonId(): Int {
        return daoStuff.getRadioBtnId()
    }

    override fun upDateSort(sortItem: Int) {
        return daoStuff.updateSort(sortItem)
    }

    class AddAsynTask(private val daoStuff: DaoStuff) : AsyncTask<StuffModelDB, Void, Void>() {
        override fun doInBackground(vararg params: StuffModelDB): Void? {
            daoStuff.insertStuff(params[0])
            return null
        }
    }
}