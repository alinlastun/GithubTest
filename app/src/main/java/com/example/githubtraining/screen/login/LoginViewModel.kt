package com.example.githubtraining.screen.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.SharedPreferences
import android.databinding.ObservableField
import android.util.Log
import com.example.githubtraining.R
import com.example.githubtraining.utill.isValidEmail
import okhttp3.Credentials
import javax.inject.Inject


class LoginViewModel @Inject constructor(private val mRepository :LoginRepository) : ViewModel() {

    val mUser = ObservableField("")
    val mPassword = ObservableField("")
    val mCredentialError = ObservableField("")

    val mSuccessLogin = MutableLiveData<Boolean>()
    val mErrorLogin = MutableLiveData<Boolean>()



    fun login() {
        mRepository.getDataUser{success, error,errorMsg ->
            if(success){
                mSuccessLogin.postValue(true)
            }else if(error){
                mCredentialError.set(errorMsg)
                mErrorLogin.postValue(true)
            }
        }

    }

    fun isValidEmail(): Boolean {
        var isValidEmail = true
        if (mUser.get().toString().isEmpty()) {
            isValidEmail = false
            mCredentialError.set("Username field is empty!")
            mErrorLogin.value = true
        }else{
            if (!mUser.get().toString().isValidEmail()) {
                isValidEmail = false
                mCredentialError.set("Your username is not a valid email!")
                mErrorLogin.value = true
            }
        }

        return isValidEmail
    }

    fun isValidPassword(): Boolean {
        var isValidPass = true
        if (mPassword.get().toString().isEmpty()) {
            isValidPass = false
            mCredentialError.set("Password field is empty!")
            mErrorLogin.value = true
        }
        return isValidPass
    }



}