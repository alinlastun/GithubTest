package com.example.githubtraining.ui.infoUser

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.githubtraining.R
import com.example.githubtraining.databinding.FragmentInfoUserBinding
import com.example.githubtraining.mvi.ChannelIntentProducer
import com.example.githubtraining.mvi.IntentProducer
import com.example.githubtraining.mvi.StateConsumer
import com.example.githubtraining.mvi.initViewModel
import com.example.githubtraining.mvi.intent
import com.example.githubtraining.ui.BaseFragment
import com.example.githubtraining.ui.infoUser.infoUserMVI.InfoUserIntent
import com.example.githubtraining.ui.infoUser.infoUserMVI.InfoUserState
import kotlinx.android.synthetic.main.fragment_info_user.*
import javax.inject.Inject

class InfoUserFragment :
    BaseFragment<FragmentInfoUserBinding>(),
    StateConsumer<InfoUserState>,
    IntentProducer<InfoUserIntent> by ChannelIntentProducer() {

    override val contentLayoutResource = R.layout.fragment_info_user
    @Inject
    lateinit var pref: SharedPreferences
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var fragmentActivity: FragmentActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel(InfoUserViewModel::class)
        binding.fragment = this
        setHasOptionsMenu(true)


        nViewRepo.setOnClickListener { intent(InfoUserIntent.BtnRepoListIntent) }
        nContactEmail.setOnClickListener { intent(InfoUserIntent.BtnRepoListIntent) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentActivity = activity!!
    }

    fun sendEmail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("develop.android@softvision.ro"))
        intent.putExtra(
            Intent.EXTRA_TEXT, "We're glad to use this app!" +
                "\nWe know that our app is far away to be perfect so, please tell us how we can do it better."
        )
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

                intent(InfoUserIntent.BtnLogoutIntent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun clearState() {
        intent(InfoUserIntent.ClearStateIntent)
    }

    override fun consume(state: InfoUserState) {
        binding.model = state
        Log.d("TimberExtensionsKt", "consume ui")
        when {

            state.navTarget == InfoUserState.NavTarget.REPO_LIST -> {
                findNavController().navigate(R.id.action_infoUserFragment_to_repositoriesFragment)
                clearState()
            }
            state.navTarget == InfoUserState.NavTarget.CONTACT_INTENT -> {
                sendEmail()
                clearState()
            }

            state.logoutBtn -> {
                findNavController().navigate(R.id.action_infoUserFragment_to_loginFragment)
                pref.edit().putString(
                    getString(R.string.sharedPrefToken),
                    getString(R.string.sharedPrefNoToken)
                ).apply()
            }


        }
    }
}