package com.example.githubtraining.ui.infoUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.githubtraining.database.dao.DaoInfoRepo
import com.example.githubtraining.database.dao.DaoInfoUser
import com.example.githubtraining.mvi.MVICoroutineViewModel
import com.example.githubtraining.ui.infoUser.infoUserMVI.InfoUserChange
import com.example.githubtraining.ui.infoUser.infoUserMVI.InfoUserIntent
import com.example.githubtraining.ui.infoUser.infoUserMVI.InfoUserState
import com.example.githubtraining.utilities.Tools
import com.softvision.hope.base.extensions.exhaustive
import javax.inject.Inject

class InfoUserViewModel @Inject constructor(
    private val mDaoInfoRepo: DaoInfoRepo,
    private val daoInfoUser: DaoInfoUser
) : MVICoroutineViewModel<InfoUserState, InfoUserIntent, InfoUserChange>() {

    override var currentState = InfoUserState()

    init {
        change(InfoUserChange.Initialize)
    }

    val mUrlAvatar: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        repo.avatar_url
    }
    val mBio: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        checkString(repo.bio)
    }
    val mLocation: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        checkString(repo.location)
    }
    val mEmail: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        checkString(repo.email)
    }
    val mCreated: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        Tools().formatMyDate(checkString(repo.created_at))
    }
    val mUpdated: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        Tools().formatMyDate(checkString(repo.updated_at))
    }
    val mPublicRepo: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        checkString(repo.total_private_repos.toString())
    }
    val mPrivateRepo: LiveData<String> = Transformations.map(daoInfoUser.getInfoUser()) { repo ->
        checkString(repo.public_repos.toString())
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
                state.copy(
                    bio = checkString(daoInfoUser.getListOfInfoUser().bio),
                    created = Tools().formatMyDate(checkString(daoInfoUser.getListOfInfoUser().created_at)),
                    email = checkString(daoInfoUser.getListOfInfoUser().email),
                    location = checkString(daoInfoUser.getListOfInfoUser().location),
                    privateRepo = checkString(daoInfoUser.getListOfInfoUser().total_private_repos.toString()),
                    publicRepo = checkString(daoInfoUser.getListOfInfoUser().public_repos.toString()),
                    urlAvatar = checkString(daoInfoUser.getListOfInfoUser().avatar_url),
                    updated = Tools().formatMyDate(checkString(daoInfoUser.getListOfInfoUser().updated_at))
                )
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
                InfoUserChange.ClickViewRepoState }
            is InfoUserIntent.BtnContactIntent -> {
                InfoUserChange.ClickContactState }
            is InfoUserIntent.ClearStateIntent -> {
                InfoUserChange.ClearButtonState }
            is InfoUserIntent.BtnLogoutIntent -> {
                InfoUserChange.LogOutState }
        }.exhaustive
}