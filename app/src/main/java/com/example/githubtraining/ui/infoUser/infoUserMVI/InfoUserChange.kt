package com.example.githubtraining.ui.infoUser.infoUserMVI

import com.example.githubtraining.db.model.UserInformation
import com.example.githubtraining.mvi.Change

sealed class InfoUserChange : Change {
    data class FetchDataDB(val userInformation: UserInformation): InfoUserChange()
    object Initialize: InfoUserChange()
    object LogOutState: InfoUserChange()
    object ClickViewRepoState: InfoUserChange()
    object ClickContactState: InfoUserChange()
    object ClearButtonState: InfoUserChange()
}