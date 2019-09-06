package com.example.githubtraining.ui.login.loginMvi

import com.example.githubtraining.mvi.Intent

sealed class LoginIntent : Intent{
    object ClearStateIntent: LoginIntent()
    object EmptyFieldEmailAddress: LoginIntent()
    object WrongFormatEmailAddress: LoginIntent()
    object NotValidPassword: LoginIntent()
    object PassFormatFields: LoginIntent()

}