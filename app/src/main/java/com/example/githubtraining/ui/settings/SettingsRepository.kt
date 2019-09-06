package com.example.githubtraining.ui.settings
import com.example.githubtraining.db.dao.DaoStuff
import com.example.githubtraining.db.model.StuffModelDB
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val daoStaff: DaoStuff) {

    val radioBtnIdFormDB = daoStaff.getRadioBtnId()
    val getStuff = daoStaff.getStuff()

    fun insertIntoStuffDB(stuffDB: StuffModelDB) {
        daoStaff.insertStuff(stuffDB)
    }
}