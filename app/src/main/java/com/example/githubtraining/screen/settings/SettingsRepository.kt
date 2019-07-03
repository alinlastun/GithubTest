package com.example.githubtraining.screen.settings

import com.example.githubtraining.database.modelDB.StuffModelDB
import com.example.githubtraining.utill.repository.RepositoryStuffDB
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val mRepositoryDB: RepositoryStuffDB) {
    val radioBtnIdFormDB = mRepositoryDB.getRadioButtonId()
    val getStuff = mRepositoryDB.getStuffFromDB()

    fun insertIntoStuffDB(stuffDB: StuffModelDB){
        mRepositoryDB.insertStuffIntoDB(stuffDB)
    }


}