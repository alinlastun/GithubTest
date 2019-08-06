package com.example.githubtraining.ui.repositories
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.ui.repoDetails.RepoDetailsActivity
import com.example.githubtraining.ui.settings.SettingsActivity
import com.example.githubtraining.utill.loading.Loading
import com.example.githubtraining.utill.setRepoAdapter2
import kotlinx.android.synthetic.main.activity_repositories.*
import javax.inject.Inject




class RepositoriesActivity : AppCompatActivity() {

    @Inject lateinit var factory: ViewModelProvider.Factory
    private lateinit var mViewModel: RepositoriesViewModel
    private lateinit var mLoading: Loading
    private var repoList :List<InfoRepoModelDB> =  mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.githubtraining.R.layout.activity_repositories)
        mLoading = Loading(this).refresh().showLoading(true)
        appComponent.inject(this)
        mViewModel = ViewModelProviders.of(this, factory).get(RepositoriesViewModel::class.java)
        nSettings.setOnClickListener { startActivity(Intent(this, SettingsActivity::class.java)) }
        nRecylerView.setRepoAdapter2(RepositoriesAdapter.RepoItemListener {
            onItemRepoClicked(it)
        })

        mViewModel.mSuccessReceive.observe(this, Observer {
            mLoading.showLoading(false)
        })
        mViewModel.mErrorReceive.observe(this, Observer {
            mLoading.showLoading(false)
            Toast.makeText(this, mViewModel.mErrorMsgReceive.get(), Toast.LENGTH_LONG).show()
        })

        mViewModel.repoList.observe(this, Observer {
            repoList=it
            (nRecylerView.adapter as RepositoriesAdapter).addHeaderAndSubmitList( it) })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(com.example.githubtraining.R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            com.example.githubtraining.R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onItemRepoClicked(idRepo: Int) {
        val intent = Intent(this, RepoDetailsActivity::class.java)
        intent.putExtra(getString(com.example.githubtraining.R.string.idRepo), idRepo)
        startActivity(intent)
    }

}
