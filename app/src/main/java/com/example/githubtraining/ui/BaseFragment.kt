package com.example.githubtraining.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.githubtraining.R
import com.example.githubtraining.mvi.HasViewModelFactory
import com.example.githubtraining.util.Injectable
import kotlinx.android.synthetic.main.fragment_base.*
import javax.inject.Inject

/**
 * Base Fragment that handles injection and provides the ViewModel Factory
 */
abstract class BaseFragment: Fragment(), Injectable, HasViewModelFactory {
    @get:LayoutRes
    abstract val contentLayoutResource: Int
    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_base, container, false).apply {
            layout_stub.layoutResource = contentLayoutResource
            layout_stub.inflate()
        }

  /*  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),contentLayoutResource, null, false)
        binding.lifecycleOwner = this
        return binding.root
    }*/
}