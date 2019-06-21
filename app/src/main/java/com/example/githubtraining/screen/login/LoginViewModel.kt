package com.example.githubtraining.screen.login

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.util.Base64
import com.example.githubtraining.utill.isValidEmail
import okhttp3.Credentials


class LoginViewModel(mContext: Context) : ViewModel() {

    val mUser = ObservableField("")
    val mPassword = ObservableField("")
    val mCredentialError = ObservableField("")
    private val mRepository = LoginRepository(this, mContext)
    val mSuccessLogin = MutableLiveData<Boolean>()
    val mErrorLogin = MutableLiveData<Boolean>()
    var encodedUserPass: String = ""


    fun login() {
        if (isValidEmail() && isValidPassword()) {
            encodedUserPass = Credentials.basic(mUser.get()!!, mPassword.get()!!)
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