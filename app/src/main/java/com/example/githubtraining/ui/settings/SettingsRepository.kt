package com.example.githubtraining.ui.settings
import com.example.githubtraining.database.dao.DaoStuff
import com.example.githubtraining.database.modelDB.StuffModelDB
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val daoStaff: DaoStuff) {

    val radioBtnIdFormDB = daoStaff.getRadioBtnId()
    val getStuff = daoStaff.getStuff()

    fun insertIntoStuffDB(stuffDB: StuffModelDB) {
        daoStaff.insertStuff(stuffDB)
    }
}