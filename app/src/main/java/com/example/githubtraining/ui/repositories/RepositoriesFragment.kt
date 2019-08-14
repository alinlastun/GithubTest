package com.example.githubtraining.ui.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.githubtraining.R
import com.example.githubtraining.appComponent
import com.example.githubtraining.databinding.FragmentRepositoriesBinding
import com.example.githubtraining.utilities.loading.Loading
import com.example.githubtraining.utilities.setRepoAdapter2
import kotlinx.android.synthetic.main.fragment_repositories.*
import javax.inject.Inject

class RepositoriesFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var mViewModel: RepositoriesViewModel
    private lateinit var mLoading: Loading

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponent.inject(this)
        val binding = FragmentRepositoriesBinding.inflate(inflater,container,false)
        mViewModel = ViewModelProviders.of(this, factory).get(RepositoriesViewModel::class.java)
        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLoading = Loading(context).refresh().showLoading(true)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        nSettings.setOnClickListener {
            findNavController().navigate(R.id.action_repositoriesFragment_to_fragmentSettings)
        }
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
        val bundle = Bundle()
        bundle.putInt(getString(com.example.githubtraining.R.string.idRepo), idRepo)
        findNavController().navigate(R.id.action_repositoriesFragment_to_repoDetailsFragment, bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.example.githubtraining.R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            com.example.githubtraining.R.id.action_settings -> {

                findNavController().navigate(com.example.githubtraining.R.id.action_infoUserFragment_to_loginFragment)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}