package com.example.githubtraining.ui.login.loginMvi

import com.example.githubtraining.mvi.State

data class LoginState(
    val errorMessage: String = "",
    val messageNotValidEmail: String = "",
    val messageNotValidPassword: String = "",
    val isSuccessNetwork: Boolean = false,
    val isOkFormatFields: Boolean = false

): State