package com.example.githubtraining.ui.repoDetails


import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
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


        Log.d("Asdfasdf", "2 ${intent.getIntExtra(getString(R.string.idRepo), -1)}")
        mViewModel.init(intent.getIntExtra(getString(R.string.idRepo), -1))

       mViewModel.resultInfoRepo.observe(this, Observer { Log.d("Asdfasdf", "3 ${it}") })

    }

}
