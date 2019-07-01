package com.example.githubtraining.screen.infoUser

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.githubtraining.R
import com.example.githubtraining.appComponent
import com.example.githubtraining.databinding.ActivityAboutUserBinding
import com.example.githubtraining.isInternetConnection
import com.example.githubtraining.screen.login.LoginActivity
import com.example.githubtraining.screen.repositories.RepositoriesActivity
import com.example.githubtraining.utill.Tools
import com.example.githubtraining.utill.ViewModelFactory
import kotlinx.android.synthetic.main.activity_about_user.*
import javax.inject.Inject

class InfoUserActivity : AppCompatActivity() {
    @Inject lateinit var pref: SharedPreferences
    @Inject lateinit var factory: ViewModelProvider.Factory
    private lateinit var mViewModel: InfoUserViewModel
    private lateinit var mBinding: ActivityAboutUserBinding
    private var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_user)
        appComponent.inject(this)
        mViewModel = ViewModelProviders.of(this, factory).get(InfoUserViewModel::class.java)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_about_user)
        mBinding.aboutUser = mViewModel
        mBinding.activity = this


        mViewModel.mValuesDataBase.observe(this, Observer {
            if(it!=null){
                for (userInfoDb in it){
                    mViewModel.mUrlAvatar.set(userInfoDb.avatar_url)
                    mViewModel.mBio.set(userInfoDb.bio)
                    mViewModel.mPrivateRepo.set(userInfoDb.total_private_repos.toString())
                    mViewModel.mPublicRepo.set(userInfoDb.public_repos.toString())
                    mViewModel.mLocation.set(userInfoDb.location.toString())
                    mViewModel.mEmail.set(userInfoDb.email.toString())
                    mViewModel.mCreated.set(userInfoDb.created_at.toString())
                    mViewModel.mUpdated.set(userInfoDb.updated_at.toString())
                    userName = userInfoDb.login.toString()
                }
            }

        })

        if(!isInternetConnection){
             if(mViewModel.mListInfoUser.size>0){
                Tools().showSnackBar(mInfoUserContainer)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                mViewModel.deleteInfoUserFromDB()
                mViewModel.deleteInfoRepo()
                pref.edit().putString(getString(R.string.sharedPrefToken),getString(R.string.sharedPrefNoToken)).apply()
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun goToRepoActivity(){
        startActivity(Intent(this,RepositoriesActivity::class.java))
    }

    fun sendEmail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("develop.android@softvision.ro"))
        intent.putExtra(Intent.EXTRA_TEXT, "We're glad to use this app!\nWe know that our app is far away to be perfect so, please tell us how we can do it better.")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
