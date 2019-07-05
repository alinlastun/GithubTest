package com.example.githubtraining.screen.repoDetails

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.githubtraining.R
import com.example.githubtraining.appComponent
import com.example.githubtraining.databinding.ActivityRepoDetailsBinding
import javax.inject.Inject

class RepoDetailsActivity : AppCompatActivity() {

    private lateinit var mViewModel: RepoDetailsViewModel
    private lateinit var mBinding: ActivityRepoDetailsBinding
    @Inject lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_repo_details)
        mViewModel = ViewModelProviders.of(this,factory).get(RepoDetailsViewModel::class.java)
        mBinding.repoDetails = mViewModel
        mBinding.lifecycleOwner = this

        mViewModel.init(intent.getIntExtra(getString(R.string.idRepo), -1))



    }

}
