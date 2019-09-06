package com.example.githubtraining.ui.login.loginMvi

import com.example.githubtraining.mvi.Change

sealed class LoginChange : Change {
    object Initialize: LoginChange()
    object Success : LoginChange()
    data class Failure(val error: String): LoginChange()
    object ClearState: LoginChange()
    object EmptyFieldEmailAddress: LoginChange()
    object WrongFormatEmailAddress: LoginChange()
    object NotValidPassword: LoginChange()
    object PassFormatFields: LoginChange()
}