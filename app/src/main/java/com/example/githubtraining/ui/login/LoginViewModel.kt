package com.example.githubtraining.ui.login

import androidx.lifecycle.viewModelScope
import com.example.githubtraining.mvi.MVICoroutineViewModel
import com.example.githubtraining.repository.LoginRepository
import com.example.githubtraining.ui.login.loginMvi.LoginChange
import com.example.githubtraining.ui.login.loginMvi.LoginIntent
import com.example.githubtraining.ui.login.loginMvi.LoginState
import com.example.githubtraining.util.ViewModel
import com.softvision.hope.base.extensions.exhaustive
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val mRepository: LoginRepository
) :
    MVICoroutineViewModel<LoginState,
        LoginIntent, LoginChange>() {

    override var currentState: LoginState = LoginState()

    init {
        change(LoginChange.Initialize)
    }

    override suspend fun mutate(state: LoginState, change: LoginChange): LoginState =
        when (change) {
            is LoginChange.Initialize -> {
                state.copy()
            }
            is LoginChange.Success -> {
                state.copy(isSuccessNetwork = true)
            }
            is LoginChange.ClearState -> {
                state.copy(
                    isSuccessNetwork = false,
                    isOkFormatFields = false,
                    errorMessage = ""
                )
            }
            is LoginChange.Failure -> {
                state.copy(errorMessage = change.error)
            }
            is LoginChange.NotValidPassword -> {
                state.copy(errorMessage = "Password field is empty!")
            }
            is LoginChange.EmptyFieldEmailAddress -> {
                state.copy(errorMessage = "Username field is empty!")
            }
            is LoginChange.WrongFormatEmailAddress -> {
                state.copy(errorMessage = "Your username is not a valid email!")
            }
            is LoginChange.PassFormatFields -> {
                viewModelScope.launch(Dispatchers.ViewModel) {
                    mRepository.getUserInfo(this) { isSuccess, errorMsg ->
                        if (isSuccess) {
                            change(LoginChange.Success)
                        } else {
                            change(LoginChange.Failure(errorMsg))
                            change(LoginChange.ClearState)
                        }
                    }
                }
                state.copy(isOkFormatFields = true)
            }
        }.exhaustive

    override suspend fun consume(intent: LoginIntent): LoginChange =
        when (intent) {
            is LoginIntent.ClearStateIntent -> {
                LoginChange.ClearState
            }
            is LoginIntent.WrongFormatEmailAddress -> {
                LoginChange.WrongFormatEmailAddress
            }
            is LoginIntent.EmptyFieldEmailAddress -> {
                LoginChange.EmptyFieldEmailAddress
            }
            is LoginIntent.NotValidPassword -> {
                LoginChange.NotValidPassword
            }
            is LoginIntent.PassFormatFields -> {
                LoginChange.PassFormatFields
            }

        }.exhaustive
}