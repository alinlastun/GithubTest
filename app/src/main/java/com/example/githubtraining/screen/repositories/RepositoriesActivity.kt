package com.example.githubtraining.screen.repositories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.githubtraining.R
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.screen.repoDetails.RepoDetailsActivity
import com.example.githubtraining.screen.settings.SettingsActivity
import com.example.githubtraining.utill.Sort
import com.example.githubtraining.utill.SortType
import com.example.githubtraining.utill.ViewModelFactory
import com.example.githubtraining.utill.setRepoAdapter
import kotlinx.android.synthetic.main.activity_repositories.*
import javax.inject.Inject


class RepositoriesActivity : AppCompatActivity() {
    @Inject
    lateinit var pref: SharedPreferences
    private lateinit var mViewModel: RepositoriesViewModel
    private var repoList :MutableList<InfoRepoModelDB> = arrayListOf()
    @Inject lateinit var factory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        appComponent.inject(this)

        mViewModel =ViewModelProviders.of(this, factory).get(RepositoriesViewModel::class.java)

        if(pref.getString(getString(R.string.sharedPrefToken),getString(R.string.sharedPrefNoToken))!=null){
            mViewModel.getDataWs((pref.getString(getString(R.string.sharedPrefToken),getString(R.string.sharedPrefNoToken)))!!)
        }

        nRecylerView.setRepoAdapter(this,mViewModel)


        mViewModel.repoListData.observe(this, Observer {
            if(it!=null){
                repoList=it
                sortByItemSelected(mViewModel.sortNr)
                (nRecylerView.adapter as RepositoriesAdapter).addData(it)
            }
        })

        Log.d("awefas43f", "1 "+mViewModel.sortNr.toString())
        mViewModel.stuffData.observe(this, Observer {
            if(it!=null){
                sortByItemSelected(it.sort)
                Log.d("awefas43f", "2 "+it.sort)
                (nRecylerView.adapter as RepositoriesAdapter).addData(repoList)
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
        val intent = Intent(this, RepoDetailsActivity::class.java)
        intent.putExtra(getString(R.string.idRepo),idRepo)
        startActivity(intent)
    }

    private fun sortByItemSelected(resultSort:Int){
        when(resultSort){
            0 ->Sort().sortListBy(repoList,SortType.CREATED)
            1 ->Sort().sortListBy(repoList,SortType.UPDATED)
            2 ->Sort().sortListBy(repoList,SortType.PUSHED)
            3 ->Sort().sortListBy(repoList,SortType.FULL_NAME)
        }
    }

}
