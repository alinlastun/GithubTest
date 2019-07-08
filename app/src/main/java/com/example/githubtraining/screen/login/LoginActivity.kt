package com.example.githubtraining.screen.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.githubtraining.MainApplication
import com.example.githubtraining.R
import com.example.githubtraining.appComponent
import com.example.githubtraining.databinding.ActivityMainBinding
import com.example.githubtraining.isInternetConnection
import com.example.githubtraining.screen.infoUser.InfoUserActivity
import com.example.githubtraining.utill.ViewModelFactory
import com.example.githubtraining.utill.loading.Loading
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Credentials
import javax.inject.Inject



class LoginActivity : AppCompatActivity() {

    @Inject lateinit var pref: SharedPreferences
    @Inject lateinit var factory: ViewModelProvider.Factory

    private lateinit var mViewModel: LoginViewModel
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mLoading :Loading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        mLoading = Loading(this).refresh()

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        mBinding.login = mViewModel

        nBtnLogin.setOnClickListener {
            encodeUserPass()
            mLoading.showLoading(true)
            if (mViewModel.isValidEmail() && mViewModel.isValidPassword()) {
                mViewModel.login()
            }
        }

        mViewModel.mSuccessLogin.observe(this, Observer {
            mLoading.showLoading(false)
            startActivity(Intent(this,InfoUserActivity::class.java))
            finish()
        })
        mViewModel.mErrorLogin.observe(this, Observer {
            mLoading.showLoading(false)
            Toast.makeText(this,mViewModel.mCredentialError.get(),Toast.LENGTH_LONG).show()

        })

    }

    private fun encodeUserPass(){
        val encodedString = Credentials.basic(mViewModel.mUser.get()!!, mViewModel.mPassword.get()!!)
        pref.edit().putString(getString(R.string.sharedPrefToken),encodedString).apply()

    }


}
