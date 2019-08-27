package com.example.githubtraining.ui.infoUser

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.githubtraining.R
import com.example.githubtraining.databinding.FragmentInfoUserBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class InfoUserFragment : DaggerFragment() {

    @Inject
    lateinit var pref: SharedPreferences
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var mViewModel: InfoUserViewModel
    lateinit var fragmentActivity:FragmentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentInfoUserBinding.inflate(inflater,container,false)
        mViewModel = ViewModelProviders.of(this, factory).get(InfoUserViewModel::class.java)
        binding.aboutUser = mViewModel
        binding.fragment = this
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentActivity = activity!!
    }

    fun goToRepoActivity() {
        findNavController().navigate(R.id.action_infoUserFragment_to_repositoriesFragment)
    }

    fun sendEmail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("develop.android@softvision.ro"))
        intent.putExtra(Intent.EXTRA_TEXT, "We're glad to use this app!" +
            "\nWe know that our app is far away to be perfect so, please tell us how we can do it better.")
        if (intent.resolveActivity(fragmentActivity.packageManager) != null) {
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                pref.edit().putString(getString(R.string.sharedPrefToken),
                    getString(R.string.sharedPrefNoToken)
                ).apply()
                findNavController().navigate(R.id.action_infoUserFragment_to_loginFragment)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



}