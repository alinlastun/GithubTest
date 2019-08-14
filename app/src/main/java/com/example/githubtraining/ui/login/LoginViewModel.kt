package com.example.githubtraining.ui.login
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubtraining.utilities.isValidEmail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
class LoginViewModel @Inject constructor(private val mRepository: LoginRepository) : ViewModel() {

    val mUser = ObservableField("")
    val mPassword = ObservableField("")
    val mCredentialError = ObservableField("")

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    val mSuccessLogin = MutableLiveData<Boolean>()
    val mErrorLogin = MutableLiveData<Boolean>()
    fun login() {
        viewModelScope.launch {
            mRepository.refreshDataUser { success, error, errorMsg ->
                if (success) {
                    mSuccessLogin.postValue(true)
                } else if (error) {
                    mCredentialError.set(errorMsg)
                    mErrorLogin.postValue(true)
                }
            }
        }
    }

    fun isValidEmail(): Boolean {
        var isValidEmail = true
        if (mUser.get().toString().isEmpty()) {
            isValidEmail = false
            mCredentialError.set("Username field is empty!")
            mErrorLogin.value = true
        } else {
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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}