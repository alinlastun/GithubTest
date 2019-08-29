package com.example.githubtraining.ui.infoUser.infoUserMVI

import com.example.githubtraining.mvi.Change

sealed class InfoUserChange : Change {
    object Initialize: InfoUserChange()
    object LogOutState: InfoUserChange()
    object ClickViewRepoState: InfoUserChange()
    object ClickContactState: InfoUserChange()
    object ClearButtonState: InfoUserChange()
}