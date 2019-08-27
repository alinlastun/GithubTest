package com.example.githubtraining.ui.login

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.githubtraining.R
import com.example.githubtraining.appComponent
import com.example.githubtraining.databinding.FragmentLoginBinding
import com.example.githubtraining.utilities.loading.Loading
import kotlinx.android.synthetic.main.fragment_login.*
import okhttp3.Credentials
import javax.inject.Inject

class LoginFragment: Fragment() {

    @Inject
    lateinit var pref: SharedPreferences
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var mViewModel: LoginViewModel
    private lateinit var mLoading: Loading

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponent.inject(this)
        val binding = FragmentLoginBinding.inflate(inflater,container, false)
        mViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        binding.login = mViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nBtnLogin.setOnClickListener {
            encodeUserPass()
            mLoading.showLoading(true)
            if (mViewModel.isValidEmail() && mViewModel.isValidPassword()) {
                mViewModel.login()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mLoading = Loading(context).refresh()
        mViewModel.mSuccessLogin.observe(this, Observer {
            Log.d("asdfasd","ajunge")
            encodeUserPass()
            mLoading.showLoading(false)
            findNavController().navigate(R.id.action_loginFragment_to_infoUserFragment)

        })
        mViewModel.mErrorLogin.observe(this, Observer {
            mLoading.showLoading(false)
            Toast.makeText(context, mViewModel.mCredentialError.get(), Toast.LENGTH_LONG).show()
        })
    }

    private fun encodeUserPass() {
        val encodedString = Credentials.basic(mViewModel.mUser.get()!!, mViewModel.mPassword.get()!!)
        pref.edit().putString(getString(R.string.sharedPrefToken), encodedString).apply()
    }
}