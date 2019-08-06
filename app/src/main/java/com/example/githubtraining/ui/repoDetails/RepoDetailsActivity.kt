package com.example.githubtraining.ui.repoDetails
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
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

        mBinding = DataBindingUtil.setContentView(this, com.example.githubtraining.R.layout.activity_repo_details)
        mViewModel = ViewModelProviders.of(this,
            factory).get(RepoDetailsViewModel::class.java)
        mBinding.repoDetails = mViewModel
        mBinding.lifecycleOwner = this

        mViewModel.init(intent.getIntExtra(getString(com.example.githubtraining.R.string.idRepo), -1))

       mViewModel.resultInfoRepo.observe(this, Observer { Log.d("Asdfasdf", "3 $it") })
    }


}