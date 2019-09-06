package com.example.githubtraining.ui.login

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.githubtraining.R
import com.example.githubtraining.databinding.FragmentLoginBinding
import com.example.githubtraining.mvi.ChannelIntentProducer
import com.example.githubtraining.mvi.IntentProducer
import com.example.githubtraining.mvi.StateConsumer
import com.example.githubtraining.mvi.initViewModel
import com.example.githubtraining.mvi.intent
import com.example.githubtraining.ui.BaseFragment
import com.example.githubtraining.ui.login.loginMvi.LoginIntent
import com.example.githubtraining.ui.login.loginMvi.LoginState
import com.example.githubtraining.utilities.isValidEmail
import com.example.githubtraining.utilities.loading.Loading
import kotlinx.android.synthetic.main.fragment_login.*
import okhttp3.Credentials
import javax.inject.Inject

class LoginFragment: BaseFragment<FragmentLoginBinding>(),
StateConsumer<LoginState>,
IntentProducer<LoginIntent> by ChannelIntentProducer(){

    override val contentLayoutResource: Int = R.layout.fragment_login
    private lateinit var mLoading: Loading
    @Inject
    lateinit var pref: SharedPreferences
    @Inject
    lateinit var factory: ViewModelProvider.Factory





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel(LoginViewModel::class)

        nBtnLogin.setOnClickListener {
            if (isValidEmail() && isValidPassword()) {
                encodeUserPass()
                intent(LoginIntent.PassFormatFields)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mLoading = Loading(context).refresh()

    }

    private fun isValidEmail(): Boolean {
        var isValidEmail = true
        if (nUsername.text.toString().isEmpty()) {
            isValidEmail = false
            intent(LoginIntent.EmptyFieldEmailAddress)
        } else {
            if (!nUsername.text.toString().isValidEmail()) {
                isValidEmail = false
                intent(LoginIntent.WrongFormatEmailAddress)
            }
        }

        return isValidEmail
    }

    private fun isValidPassword(): Boolean {
        var isValidPass = true
        if (nPassword.text.toString().isEmpty()) {
            isValidPass = false
            intent(LoginIntent.NotValidPassword)
        }
        return isValidPass
    }
    private fun encodeUserPass() {
        val encodedString = Credentials.basic(nUsername.text.toString(), nPassword.text.toString())
        pref.edit().putString(getString(R.string.sharedPrefToken), encodedString).apply()
    }
    override fun consume(state: LoginState) {
       if(state.isSuccessNetwork){
            findNavController().navigate(R.id.action_loginFragment_to_infoUserFragment)
           intent(LoginIntent.ClearStateIntent)
        }else if (state.errorMessage.isNotEmpty()){
            Toast.makeText(context, state.errorMessage, Toast.LENGTH_LONG).show()
           intent(LoginIntent.ClearStateIntent)
        }
    }
}