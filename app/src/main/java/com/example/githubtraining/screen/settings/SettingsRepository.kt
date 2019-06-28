package com.example.githubtraining.screen.settings

import com.example.githubtraining.utill.repository.RepositoryStuffDB
import javax.inject.Inject

class SettingsRepository @Inject constructor( val mRepositorDB: RepositoryStuffDB) {
    var radioBtnIdFormDB = mRepositorDB.getRadioButtonId()


}