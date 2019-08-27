package com.example.githubtraining.ui.repoDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.githubtraining.databinding.FragmentPagerBinding
import com.example.githubtraining.ui.repositories.RepositoriesViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_pager.*
import javax.inject.Inject

class FragmentViewPager: DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var mViewModel: RepositoriesViewModel
    lateinit var pagerAdapter: CustomFragmentAdapter
    private val args: FragmentViewPagerArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPagerBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProviders.of(this, factory).get(RepositoriesViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pagerAdapter = CustomFragmentAdapter(requireActivity())
        pagerAdapter.updateList(mViewModel.getRepoList())

        val pagePosition = mViewModel.getRepoList().indexOfFirst { it.id == args.repoId }
        view_pager.currentItem = pagePosition
        view_pager.adapter = pagerAdapter
    }
}