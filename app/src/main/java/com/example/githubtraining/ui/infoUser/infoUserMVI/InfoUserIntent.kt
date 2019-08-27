package com.example.githubtraining.ui.infoUser.infoUserMVI

import com.example.githubtraining.mvi.Intent

sealed class InfoUserIntent : Intent{
    object BtnClickedIntent: InfoUserIntent()
}