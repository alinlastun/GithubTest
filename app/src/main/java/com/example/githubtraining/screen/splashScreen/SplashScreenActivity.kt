package com.example.githubtraining.screen.splashScreen

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.githubtraining.R
import com.example.githubtraining.appComponent
import com.example.githubtraining.screen.infoUser.InfoUserActivity
import com.example.githubtraining.screen.login.LoginActivity
import com.example.githubtraining.screen.login.LoginViewModel
import com.example.githubtraining.utill.LocalViewModelFactory
import javax.inject.Inject


class SplashScreenActivity : AppCompatActivity() {

    @Inject
    lateinit var pref:SharedPreferences
    private lateinit var mViewModel: LoginViewModel
    private var token:String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.injectSP(this)
        mViewModel = ViewModelProviders.of(this, LocalViewModelFactory(this)).get(LoginViewModel::class.java)

        token = pref.getString(getString(R.string.sharedPrefToken),getString(R.string.sharedPrefNoToken))


        Log.d("asdfasd",token)
        if(token!=getString(R.string.sharedPrefNoToken)){
            Log.d("asdfasd","1")
            mViewModel.login(token!!)
            startActivity(Intent(this, InfoUserActivity::class.java))
            finish()

        }else{
            Log.d("asdfasd","2")
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}