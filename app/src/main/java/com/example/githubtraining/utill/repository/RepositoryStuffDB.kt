package com.example.githubtraining.utill.repository

import androidx.lifecycle.LiveData
import com.example.githubtraining.database.dao.DaoStuff
import com.example.githubtraining.database.modelDB.StuffModelDB
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Named

open class RepositoryStuffDB @Inject constructor(private val daoStuff: DaoStuff, @Named("DiskExecutor") private val  diskExecutor : Executor):Stuff {

    override fun getStuffFromDB(): LiveData<StuffModelDB> {
        return daoStuff.getStuff()
    }

    override fun getStuffListFromDB(): MutableList<StuffModelDB> {
        return daoStuff.getStuffList()
    }

    override fun insertStuffIntoDB(stuffDB: StuffModelDB) {
        diskExecutor.execute {  daoStuff.insertStuff(stuffDB)}
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

}