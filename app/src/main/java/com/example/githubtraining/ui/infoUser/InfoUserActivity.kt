package com.example.githubtraining.ui.infoUser
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.githubtraining.R
import com.example.githubtraining.appComponent
import com.example.githubtraining.databinding.ActivityAboutUserBinding
import com.example.githubtraining.ui.login.LoginActivity
import com.example.githubtraining.ui.repositories.RepositoriesActivity
import javax.inject.Inject

class InfoUserActivity : AppCompatActivity() {

    @Inject lateinit var pref: SharedPreferences
    @Inject lateinit var factory: ViewModelProvider.Factory
    private lateinit var mViewModel: InfoUserViewModel
    private lateinit var mBinding: ActivityAboutUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_user)
        appComponent.inject(this)
        mViewModel = ViewModelProviders.of(this, factory).get(InfoUserViewModel::class.java)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_about_user)
        mBinding.aboutUser = mViewModel
        mBinding.activity = this
        mBinding.lifecycleOwner = this

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                pref.edit().putString(getString(R.string.sharedPrefToken),
                    getString(R.string.sharedPrefNoToken)
                ).apply()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun goToRepoActivity() {
         startActivity(Intent(this, RepositoriesActivity::class.java))
    }

    fun sendEmail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("develop.android@softvision.ro"))
        intent.putExtra(Intent.EXTRA_TEXT, "We're glad to use this app!" +
            "\nWe know that our app is far away to be perfect so, please tell us how we can do it better.")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}