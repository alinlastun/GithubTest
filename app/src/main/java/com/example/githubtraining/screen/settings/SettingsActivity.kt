package com.example.githubtraining.screen.settings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.githubtraining.R
import kotlinx.android.synthetic.main.activity_settings.*
import android.arch.lifecycle.ViewModelProviders
import com.example.githubtraining.screen.login.LoginViewModel
import com.example.githubtraining.utill.LocalViewModelFactory
import kotlinx.android.synthetic.main.content_settings.*


class SettingsActivity : AppCompatActivity() {

    private lateinit var mViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)

        mViewModel =ViewModelProviders.of(this, LocalViewModelFactory(this)).get(SettingsViewModel::class.java)

    }

}
