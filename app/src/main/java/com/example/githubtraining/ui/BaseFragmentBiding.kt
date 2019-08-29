package com.example.githubtraining.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
abstract class BaseFragmentBiding<VDB :ViewDataBinding >: DaggerFragment(), Injectable, HasViewModelFactory {
    @get:LayoutRes
    abstract val contentLayoutResource: Int
    @Inject
    lateinit var binding: VDB
    override lateinit var viewModelFactory: ViewModelProvider.Factory

   /* @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_base, container, false).apply {
            layout_stub.layoutResource = contentLayoutResource
            layout_stub.inflate()
        }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,contentLayoutResource, null, false)
        binding.lifecycleOwner = this
        return binding.root
    }


    /*   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
           appComponent.inject(this)
           val binding = FragmentInfoUserBinding.inflate(inflater,container,false)
           mViewModel = ViewModelProviders.of(this, factory).get(InfoUserViewModel::class.java)
           binding.aboutUser = mViewModel
           binding.fragment = this
           binding.lifecycleOwner = this
           setHasOptionsMenu(true)
           return binding.root
       }*/
}