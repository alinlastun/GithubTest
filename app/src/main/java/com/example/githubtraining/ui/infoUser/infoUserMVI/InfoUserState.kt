package com.example.githubtraining.ui.infoUser.infoUserMVI

import com.example.githubtraining.mvi.State

data class InfoUserState  (
    val urlAvatar: String? = "",
    val bio: String? = "",
    val location: String? = "",
    val email: String? = "",
    val created: String? = "",
    val updated: String? = "",
    val publicRepo: String? = "",
    val privateRepo: String? = "",
    val generalData: String = "",
    var navTarget: NavTarget? = null,
    val navArgs: Any? = null,
    val logoutBtn:Boolean = false
): State {
    enum class NavTarget { REPO_LIST, CONTACT_INTENT, NULL }
}