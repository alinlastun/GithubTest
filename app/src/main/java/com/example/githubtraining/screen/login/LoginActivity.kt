package com.example.githubtraining.screen.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.githubtraining.R
import com.example.githubtraining.databinding.ActivityMainBinding
import com.example.githubtraining.screen.infoUser.InfoUserActivity
import com.example.githubtraining.utill.LocalViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class LoginActivity : AppCompatActivity() {


    private lateinit var mViewModel: LoginViewModel
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel =ViewModelProviders.of(this, LocalViewModelFactory(this)).get(LoginViewModel::class.java)
        mBinding.login = mViewModel

        nBtnLogin.setOnClickListener { mViewModel.login()}
        mViewModel.mSuccessLogin.observe(this, Observer { startActivity(Intent(this,InfoUserActivity::class.java))
            finish()
        })
        mViewModel.mErrorLogin.observe(this, Observer { Toast.makeText(this,mViewModel.mCredentialError.get(),Toast.LENGTH_LONG).show() })

    }


}
