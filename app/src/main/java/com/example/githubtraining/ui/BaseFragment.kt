package com.example.githubtraining.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.githubtraining.mvi.HasViewModelFactory
import com.example.githubtraining.util.Injectable
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Base Fragment that handles injection and provides the ViewModel Factory
 */
abstract class BaseFragment<VDB : ViewDataBinding> : DaggerFragment(),
    Injectable, HasViewModelFactory {

    @get:LayoutRes
    abstract val contentLayoutResource: Int

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: VDB

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            contentLayoutResource, null, false
        )
        binding.lifecycleOwner = this
        return binding.root
    }
}