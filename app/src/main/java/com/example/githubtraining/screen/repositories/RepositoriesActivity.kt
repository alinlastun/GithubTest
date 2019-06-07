package com.example.githubtraining.screen.repositories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.githubtraining.R
import com.example.githubtraining.screen.repoDetails.RepoDetailsActivity
import com.example.githubtraining.screen.settings.SettingsActivity
import com.example.githubtraining.utill.LocalViewModelFactory
import com.example.githubtraining.utill.setRepoAdapter
import kotlinx.android.synthetic.main.activity_repositories.*


class RepositoriesActivity : AppCompatActivity() {

    private lateinit var mViewModel: RepositoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        mViewModel =ViewModelProviders.of(this, LocalViewModelFactory(this)).get(RepositoriesViewModel::class.java)

        mViewModel.getDataWs()
        nRecylerView.setRepoAdapter(this)


        mViewModel.repoListData.observe(this, Observer {
            if(it!=null){
                Log.d("asfasdf", mViewModel.repoData.size.toString())

                (nRecylerView.adapter as RepositoriesAdapter).addData(it)
            }
        })
        nSettings.setOnClickListener { startActivity(Intent(this,SettingsActivity::class.java)) }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun onItemRepoClicked(idRepo:Int){
        Log.d("asdfasf", idRepo.toString())
        val intent = Intent(this, RepoDetailsActivity::class.java)
        intent.putExtra("idRepo",idRepo)
        startActivity(intent)
    }

}
