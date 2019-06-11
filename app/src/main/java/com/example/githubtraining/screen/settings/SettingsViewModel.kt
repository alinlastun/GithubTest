package com.example.githubtraining.screen.settings

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.githubtraining.database.modelDB.StuffModelDB

class SettingsViewModel(mContext:Context):ViewModel() {
   private var repository  = SettingsRepository(mContext)
    var radioBtnId = repository.radioBtnIdFormDB

    fun insertIntoStuffDB(stuffDB: StuffModelDB){
        repository.mRepositorDB.insertStuffIntoDB(stuffDB)
    }

}