package com.example.githubtraining.ui.splashScreen


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.githubtraining.R
import com.example.githubtraining.appComponent
import com.example.githubtraining.ui.infoUser.InfoUserActivity
import com.example.githubtraining.ui.login.LoginActivity
import com.example.githubtraining.ui.login.LoginViewModel
import javax.inject.Inject


class SplashScreenActivity : AppCompatActivity() {

    @Inject lateinit var pref:SharedPreferences
    @Inject lateinit var factory: ViewModelProvider.Factory
    private lateinit var mViewModel: LoginViewModel
    private var token:String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)
        mViewModel = ViewModelProviders.of(this,factory).get(LoginViewModel::class.java)

        token = pref.getString(getString(R.string.sharedPrefToken),getString(R.string.sharedPrefNoToken))

        if(token!=getString(R.string.sharedPrefNoToken)){
            mViewModel.login()
            startActivity(Intent(this, InfoUserActivity::class.java))
            finish()

        }else{
            Log.d("asdfasd","2")
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}