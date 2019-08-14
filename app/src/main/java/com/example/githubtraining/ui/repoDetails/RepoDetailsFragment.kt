package com.example.githubtraining.ui.repoDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.githubtraining.R
import com.example.githubtraining.appComponent
import com.example.githubtraining.databinding.FragmentRepoDetailsBinding
import javax.inject.Inject

class RepoDetailsFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var mViewModel: RepoDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRepoDetailsBinding.inflate(inflater,container,false)
        appComponent.inject(this)
        mViewModel = ViewModelProviders.of(this, factory).get(RepoDetailsViewModel::class.java)
        binding.repoDetails = mViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       val idRepo =  arguments?.getInt(getString(R.string.idRepo))
        if (idRepo != null) {
            mViewModel.init(idRepo)
        }
    }
}