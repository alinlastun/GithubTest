package com.example.githubtraining.ui.infoUser

import androidx.lifecycle.viewModelScope
import com.example.githubtraining.db.dao.DaoInfoUser
import com.example.githubtraining.mvi.MVICoroutineViewModel
import com.example.githubtraining.ui.infoUser.infoUserMVI.InfoUserChange
import com.example.githubtraining.ui.infoUser.infoUserMVI.InfoUserIntent
import com.example.githubtraining.ui.infoUser.infoUserMVI.InfoUserState
import com.example.githubtraining.util.ViewModel
import com.example.githubtraining.utilities.Tools
import com.softvision.hope.base.extensions.exhaustive
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoUserViewModel @Inject constructor(
    private val daoInfoUser: DaoInfoUser
) : MVICoroutineViewModel<InfoUserState, InfoUserIntent, InfoUserChange>() {

    override var currentState = InfoUserState()

    init {
        change(InfoUserChange.Initialize)
    }

    private fun checkString(value: String?): String {
        var myString: String? = value
        if (value == null) {
            myString = "No Information"
        }
        return myString!!
    }

    override suspend fun mutate(state: InfoUserState, change: InfoUserChange): InfoUserState =
        when (change) {
            is InfoUserChange.Initialize -> {
                viewModelScope.launch(Dispatchers.ViewModel){
                    daoInfoUser.getAllInfoUser().collect {
                        change(InfoUserChange.FetchDataDB(it))
                    }
                }
                state.copy()
            }
            is InfoUserChange.FetchDataDB -> {
                state.copy(
                    bio = checkString(change.userInformation.bio),
                    created = Tools().formatMyDate(checkString(change.userInformation.createdAt)),
                    email = checkString(change.userInformation.email),
                    location = checkString(change.userInformation.location),
                    privateRepo = checkString(change.userInformation.privateRepos.toString()),
                    publicRepo = checkString(change.userInformation.publicRepos.toString()),
                    urlAvatar = checkString(change.userInformation.avatarUrl),
                    updated = Tools().formatMyDate(checkString(change.userInformation.updatedAt)))
            }
            is InfoUserChange.ClearButtonState -> {
                state.copy(
                    navTarget = InfoUserState.NavTarget.NULL,
                    logoutBtn = false
                )
            }
            is InfoUserChange.ClickViewRepoState -> {
                state.copy( navTarget = InfoUserState.NavTarget.REPO_LIST )
            }
            is InfoUserChange.LogOutState ->{
                state.copy( logoutBtn = true )
            }
            is InfoUserChange.ClickContactState -> {
                state.copy( navTarget = InfoUserState.NavTarget.CONTACT_INTENT
                )
            }
            InfoUserChange.LogOutState -> state.copy()
        }.exhaustive

    override suspend fun consume(intent: InfoUserIntent): InfoUserChange =
        when (intent) {
            is InfoUserIntent.BtnRepoListIntent -> {
                InfoUserChange.ClickViewRepoState
            }
            is InfoUserIntent.BtnContactIntent -> {
                InfoUserChange.ClickContactState }
            is InfoUserIntent.ClearStateIntent -> {
                InfoUserChange.ClearButtonState }
            is InfoUserIntent.BtnLogoutIntent -> {
                InfoUserChange.LogOutState }
        }.exhaustive
}