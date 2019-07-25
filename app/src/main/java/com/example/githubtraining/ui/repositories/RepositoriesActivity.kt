package com.example.githubtraining.ui.repositories

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.githubtraining.R
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.database.modelDB.StuffModelDB
import com.example.githubtraining.ui.repoDetails.RepoDetailsActivity
import com.example.githubtraining.ui.settings.SettingsActivity
import com.example.githubtraining.utill.Sort
import com.example.githubtraining.utill.enums.SortType
import com.example.githubtraining.utill.loading.Loading
import com.example.githubtraining.utill.setRepoAdapter2
import kotlinx.android.synthetic.main.activity_repositories.*
import javax.inject.Inject


class RepositoriesActivity : AppCompatActivity() {

    @Inject lateinit var factory: ViewModelProvider.Factory

    private lateinit var mViewModel: RepositoriesViewModel
    private var repoList: MutableList<InfoRepoModelDB> = arrayListOf()
    private var repoListCollaborator: MutableList<InfoRepoModelDB> = arrayListOf()
    private var  repoListOwner: MutableList<InfoRepoModelDB> = arrayListOf()
    private lateinit var mLoading: Loading


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)
        mLoading = Loading(this).refresh().showLoading(true)
        appComponent.inject(this)
        mViewModel = ViewModelProviders.of(this, factory).get(RepositoriesViewModel::class.java)

        nRecylerView.setRepoAdapter2(RepositoriesAdapter.RepoItemListener{
            onItemRepoClicked(it)
        })

        mViewModel.mSuccessReceive.observe(this, Observer {
            mLoading.showLoading(false)
        })
        mViewModel.mErrorReceive.observe(this, Observer {
            mLoading.showLoading(false)
            Toast.makeText(this, mViewModel.mErrorMsgReceive.get(), Toast.LENGTH_LONG).show()
        })


        mViewModel.infoRepoLiveData.observe(this, Observer {
            if (it != null) {
                repoList = it.toMutableList()
                getCollaboratorList(it.toMutableList())
                sortByItemSelected(mViewModel.sortNr)
                for (stuff in mViewModel.stuffList) {
                    showListBySort(stuff)
                }
                if (mViewModel.stuffList.size < 1) {
                    (nRecylerView.adapter as RepositoriesAdapter).addHeaderAndSubmitList(repoListOwner)
                }
                if (it.size > 0) {
                    mLoading.showLoading(false)
                }
            }
        })

        mViewModel.stuffData.observe(this, Observer {
            if (it != null) {
                sortByItemSelected(it.sort)
                showListBySort(it)
            }
        })

        nSettings.setOnClickListener { startActivity(Intent(this, SettingsActivity::class.java)) }

    }

    private fun showListBySort(stuffModelDB: StuffModelDB) {
        if (stuffModelDB.collaborator && !stuffModelDB.owner) {
            (nRecylerView.adapter as RepositoriesAdapter).addHeaderAndSubmitList(repoListCollaborator)
        } else if (stuffModelDB.owner && !stuffModelDB.collaborator) {
            (nRecylerView.adapter as RepositoriesAdapter).addHeaderAndSubmitList(repoListOwner)
        } else if (stuffModelDB.owner && stuffModelDB.collaborator) {
            (nRecylerView.adapter as RepositoriesAdapter).addHeaderAndSubmitList(repoList)
        }
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

    private fun onItemRepoClicked(idRepo: Int) {
        val intent = Intent(this, RepoDetailsActivity::class.java)
        Log.d("Asdfasdf", "1 $idRepo")
        intent.putExtra(getString(R.string.idRepo), idRepo)
        startActivity(intent)
    }

    private fun sortByItemSelected(resultSort: Int) {
        when (resultSort) {
            0 -> Sort().sortListBy(repoList, SortType.CREATED)
            1 -> Sort().sortListBy(repoList, SortType.UPDATED)
            2 -> Sort().sortListBy(repoList, SortType.PUSHED)
            3 -> Sort().sortListBy(repoList, SortType.FULL_NAME)
        }
    }

    private fun getCollaboratorList(list: MutableList<InfoRepoModelDB>) {
        repoListCollaborator.clear()
        repoListOwner.clear()
        for (value in list) {
            if (!value.full_name?.contains(mViewModel.userNameLogged)!!) {
                repoListCollaborator.add(value)
            } else if (value.full_name?.contains(mViewModel.userNameLogged)!!) {
                repoListOwner.add(value)
            }
        }
    }

}
