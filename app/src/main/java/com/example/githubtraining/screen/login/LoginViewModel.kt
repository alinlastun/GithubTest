package com.example.githubtraining.screen.login

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.util.Base64
import android.util.Log
import com.example.githubtraining.utill.isValidEmail
import okhttp3.Credentials
import javax.inject.Inject


class LoginViewModel @Inject constructor(private val mRepository :LoginRepository) : ViewModel() {

    val mUser = ObservableField("")
    val mPassword = ObservableField("")
    val mCredentialError = ObservableField("")

    val mSuccessLogin = MutableLiveData<Boolean>()
    val mErrorLogin = MutableLiveData<Boolean>()


    fun login(encodedUserPass:String) {
            mRepository.login(encodedUserPass)
    }

     fun isValidEmail(): Boolean {
        var isValidEmail = true
         Log.d("asdfasdf","isValidEmail 1: ${mUser.get()}")
        if (mUser.get().toString().isEmpty()) {
            Log.d("asdfasdf","isValidEmail 2: ${mUser.get()}")
            isValidEmail = false
            mCredentialError.set("Username field is empty!")
            mErrorLogin.value = true
        }else{
            Log.d("asdfasdf","isValidEmail 3")
            if (!mUser.get().toString().isValidEmail()) {
                Log.d("asdfasdf","isValidEmail 4")
                isValidEmail = false
                mCredentialError.set("Your username is not a valid email!")
                mErrorLogin.value = true
            }
        }

        return isValidEmail
    }

     fun isValidPassword(): Boolean {
        var isValidPass = true
         Log.d("asdfasdf","isValidEmail 5 ${mPassword.get()}")
        if (mPassword.get().toString().isEmpty()) {
            isValidPass = false
            Log.d("asdfasdf","isValidEmail 6")
            mCredentialError.set("Password field is empty!")
            mErrorLogin.value = true
        }
        return isValidPass
    }

}