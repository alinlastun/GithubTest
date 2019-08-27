package com.example.githubtraining.ui.infoUser.infoUserMVI

import com.example.githubtraining.mvi.Change

sealed class InfoUserChange : Change {
    object Initialize: InfoUserChange()
    object LogOutState: InfoUserChange()
    object ViewRepoState: InfoUserChange()
    object ContactState: InfoUserChange()
}