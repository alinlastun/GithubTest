package com.example.githubtraining.screen.repoDetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.githubtraining.R
import com.example.githubtraining.databinding.ActivityRepoDetailsBinding
import com.example.githubtraining.utill.LocalViewModelFactory

class RepoDetailsActivity : AppCompatActivity() {

    private lateinit var mViewModel: RepoDetailsViewModel
    private lateinit var mBinding: ActivityRepoDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_repo_details)
        mViewModel = ViewModelProviders.of(this).get(RepoDetailsViewModel::class.java)
        mBinding.repoDetails = mViewModel

        mViewModel.getRepoFromDBById(intent.getIntExtra(getString(R.string.idRepo), -1)).observe(this, Observer {
            if (it != null) {
                if(it.private!!){
                    mViewModel.mStatusRepository.set("private")
                }else{
                    mViewModel.mStatusRepository.set("public")
                }
                mViewModel.mNameRepository.set(it.name)
                if (it.description == null) {
                    mViewModel.mDescriptionRepository.set(getString(R.string.no_description))
                } else {
                    mViewModel.mDescriptionRepository.set(it.description)
                }

            }

        })


    }

}
