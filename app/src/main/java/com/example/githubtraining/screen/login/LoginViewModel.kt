package com.example.githubtraining.screen.login

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.util.Base64
import com.example.githubtraining.utill.isValidEmail


class LoginViewModel(mContext: Context) : ViewModel() {

    val mUser = ObservableField("alinlastun@gmail.com")
    val mPassword = ObservableField("github@0306")
    val mCredentialError = ObservableField("")
    private val mRepository = LoginRepository(this, mContext)
    val mSuccessLogin = MutableLiveData<Boolean>()
    val mErrorLogin = MutableLiveData<Boolean>()
    var encodedUserPass: String = ""


    fun login() {
        if (isValidEmail() && isValidPassword()) {
            val password = "${mUser.get()}:${mPassword.get()}"
            val data = password.toByteArray(charset("UTF-8"))
            encodedUserPass ="Basic "+ Base64.encodeToString(data, Base64.NO_WRAP)
            mRepository.login(encodedUserPass)
        }
    }

    private fun isValidEmail(): Boolean {
        var isValidEmail = true
        if (mUser.get().toString().isEmpty()) {
            isValidEmail = false
            mCredentialError.set("Username field is empty!")
            mErrorLogin.value = true
        }
        if (!mUser.get().toString().isValidEmail()) {
            isValidEmail = false
            mCredentialError.set("Your username is not a valid email!")
            mErrorLogin.value = true

        }
        return isValidEmail
    }

    private fun isValidPassword(): Boolean {
        var isValidPass = true
        if (mPassword.get().toString().isEmpty()) {
            isValidPass = false
            mCredentialError.set("Password field is empty!")
            mErrorLogin.value = true
        }
        return isValidPass
    }

}