package com.example.githubtraining.screen.splashScreen

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.githubtraining.database.AppDataBase
import com.example.githubtraining.screen.infoUser.InfoUserActivity
import com.example.githubtraining.screen.login.LoginActivity


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appDataBase = AppDataBase.getDataBase(this).daoInfoUser()
        appDataBase.getInfoUser().observe(this, Observer {
            if (it != null) {
                if (it.size > 0) {
                    for (infoUserDB in it) {
                        if (infoUserDB.isLogin) {
                            val intent = Intent(applicationContext, InfoUserActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            val intent = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }

        })


    }
}