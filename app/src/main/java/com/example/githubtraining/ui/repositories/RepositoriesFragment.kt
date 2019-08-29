package com.example.githubtraining.ui.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.githubtraining.R
import com.example.githubtraining.databinding.FragmentRepositoriesBinding
import com.example.githubtraining.utilities.loading.Loading
import com.example.githubtraining.utilities.setRepoAdapter2
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_repositories.*
import javax.inject.Inject

class RepositoriesFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var mViewModel: RepositoriesViewModel
    private lateinit var mLoading: Loading

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRepositoriesBinding.inflate(inflater,container,false)
        mViewModel = ViewModelProviders.of(this, factory).get(RepositoriesViewModel::class.java)
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLoading = Loading(context).refresh().showLoading(true)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        nRecylerView.setRepoAdapter2(RepositoriesAdapter.RepoItemListener {
            onItemRepoClicked(it)
        })

        mViewModel.mSuccessReceive.observe(this, Observer {
            mLoading.showLoading(false)
        })
        mViewModel.mErrorReceive.observe(this, Observer {
            mLoading.showLoading(false)
            Toast.makeText(context, mViewModel.mErrorMsgReceive.get(), Toast.LENGTH_LONG).show()
        })
        mViewModel.infoRepoLiveData.observe(this, Observer {
            (nRecylerView.adapter as RepositoriesAdapter).addHeaderAndSubmitList(mViewModel.getRepoList())

        })
    }

    private fun onItemRepoClicked(idRepo: Int) {
        findNavController().navigate(
            RepositoriesFragmentDirections.actionRepositoriesFragmentToFragmentPager(idRepo)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main2, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
           R.id.action_settings -> {
                findNavController().navigate(R.id.action_repositoriesFragment_to_fragmentSettings)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    

}