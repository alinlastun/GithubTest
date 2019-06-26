package com.example.githubtraining.screen.login

import android.arch.lifecycle.Observer
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
import com.example.githubtraining.utill.LocalViewModelFactory
import com.example.githubtraining.utill.Tools
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Credentials
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var pref:SharedPreferences

    private lateinit var mViewModel: LoginViewModel
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel =ViewModelProviders.of(this, LocalViewModelFactory(this)).get(LoginViewModel::class.java)
        mBinding.login = mViewModel

        nBtnLogin.setOnClickListener {
            if (mViewModel.isValidEmail() && mViewModel.isValidPassword()) {
                mViewModel.login(encodeUserPass())
            }


        }

        mViewModel.mSuccessLogin.observe(this, Observer { startActivity(Intent(this,InfoUserActivity::class.java))
            isInternetConnection=true
            if(pref.getString(getString(R.string.sharedPrefToken),getString(R.string.sharedPrefNoToken))!=(getString(R.string.sharedPrefNoToken))){
                pref.edit().putString(encodeUserPass(),getString(R.string.sharedPrefNoToken)).apply()
            }

            finish()
        })
        mViewModel.mErrorLogin.observe(this, Observer {
            isInternetConnection=false
            Toast.makeText(this,mViewModel.mCredentialError.get(),Toast.LENGTH_LONG).show()
        })

    }

    private fun encodeUserPass():String{
       val encodedString = Credentials.basic(mViewModel.mUser.get()!!, mViewModel.mPassword.get()!!)
            pref.edit().putString(getString(R.string.sharedPrefToken),encodedString).apply()
        return encodedString
    }


}
