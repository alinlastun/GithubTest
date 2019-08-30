package com.example.githubtraining.ui.infoUser

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.githubtraining.database.dao.DaoInfoUser
import com.example.githubtraining.database.modelDB.UserInformationModelDB
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
                    daoInfoUser.getInfoUser().collect {
                        Log.d("asdfasdf","FetchDataDB collect")
                        fetchDataBase(it)
                    }
                }
                state.copy(

                )
            }
            is InfoUserChange.FetchDataDB -> {
                Log.d("asdfasdf","FetchDataDB")
                state.copy(
                    bio = checkString(change.userInformationModelDB.bio),
                    created = Tools().formatMyDate(checkString(change.userInformationModelDB.created_at)),
                    email = checkString(change.userInformationModelDB.email),
                    location = checkString(change.userInformationModelDB.location),
                    privateRepo = checkString(change.userInformationModelDB.total_private_repos.toString()),
                    publicRepo = checkString(change.userInformationModelDB.public_repos.toString()),
                    urlAvatar = checkString(change.userInformationModelDB.avatar_url),
                    updated = Tools().formatMyDate(checkString(change.userInformationModelDB.updated_at)))
            }
            is InfoUserChange.ClearButtonState -> {
                state.copy(
                    navTarget = InfoUserState.NavTarget.NULL,
                    logoutBtn = false
                )
            }
            is InfoUserChange.ClickViewRepoState -> {
                Log.d("asdfasdf"," ClickViewRepoState")
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

    private  fun fetchDataBase(userInformationModelDB: UserInformationModelDB){
        Log.d("asdfasdf","FetchDataDB method")
        InfoUserIntent.BtnRepoListIntent
        InfoUserChange.FetchDataDB(userInformationModelDB)
        Log.d("asdfasdf","FetchDataDB method 2")

    }

    override suspend fun consume(intent: InfoUserIntent): InfoUserChange =
        when (intent) {
            is InfoUserIntent.BtnRepoListIntent -> {
                InfoUserChange.ClickViewRepoState }
            is InfoUserIntent.BtnContactIntent -> {
                InfoUserChange.ClickContactState }
            is InfoUserIntent.ClearStateIntent -> {
                InfoUserChange.ClearButtonState }
            is InfoUserIntent.BtnLogoutIntent -> {
                InfoUserChange.LogOutState }
        }.exhaustive
}