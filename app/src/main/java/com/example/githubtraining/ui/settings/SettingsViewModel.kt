package com.example.githubtraining.ui.settings

import androidx.lifecycle.ViewModel
import com.example.githubtraining.database.modelDB.StuffModelDB
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
) : ViewModel() {

    var radioBtnId = repository.radioBtnIdFormDB
    var getStuffLiveData = repository.getStuff

    fun insertStuff(stuffDB: StuffModelDB) {
        repository.insertIntoStuffDB(stuffDB)
    }
}