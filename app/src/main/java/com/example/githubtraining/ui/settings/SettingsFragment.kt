package com.example.githubtraining.ui.settings

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.githubtraining.R
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.modelDB.StuffModelDB
import com.example.githubtraining.databinding.FragmentSettingsBinding
import javax.inject.Inject

class SettingsFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var mViewModel: SettingsViewModel
    private val stuffDb = StuffModelDB()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponent.inject(this)
        val binding = FragmentSettingsBinding.inflate(inflater,container,false)
        mViewModel = ViewModelProviders.of(this, factory).get(SettingsViewModel::class.java)
        binding.fragment = this
        return binding.root
    }


    fun onClickSortRepo() {
        showSortDialog()
    }

    fun onClickAffiliationRepo() {
        showAffiliationDialog()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.getStuffLiveData.observe(this, Observer {
            stuffDb.owner = it.owner
            stuffDb.collaborator = it.collaborator
            stuffDb.organizationMember = it.organizationMember
        })
    }


    private fun showSortDialog() {
        val dialogBuilder = context?.let { AlertDialog.Builder(it) }
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_sort, null)
        dialogBuilder?.setView(dialogView)
        dialogBuilder?.setCancelable(false)
        val alertDialog = dialogBuilder?.create()

        val btnCancel = dialogView.findViewById<TextView>(R.id.nCancelDialog)
        btnCancel?.setOnClickListener { alertDialog?.dismiss() }

        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.nRadioGroup)

        if (mViewModel.radioBtnId > 0) {
            val rb = dialogView.findViewById<RadioButton>(mViewModel.radioBtnId)
            rb.isChecked = true
        }
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val rb = dialogView.findViewById<RadioButton>(checkedId)

            stuffDb.idRadioButton = checkedId
            stuffDb.sort = radioGroup.indexOfChild(rb)
            mViewModel.insertStuff(stuffDb)
        }
        alertDialog?.show()
    }

    private fun showAffiliationDialog() {
        lateinit var dialog: AlertDialog
        val arrayColors = arrayOf("Owner","Collaborator","Organization Member")
        val myArray = arrayListOf(stuffDb.owner,
            stuffDb.collaborator,
            stuffDb.organizationMember)
        val arrayChecked: BooleanArray = myArray.toBooleanArray()
        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.setTitle("Affiliation")
        builder?.setMultiChoiceItems(arrayColors, arrayChecked) { _, which, isChecked ->
            arrayChecked[which] = isChecked
            when (which) {
                0 -> stuffDb.owner = isChecked
                1 -> stuffDb.collaborator = isChecked
                2 -> stuffDb.organizationMember = isChecked
            }
        }
        builder?.setPositiveButton("OK") { _, _ ->
            mViewModel.insertStuff(stuffDb)
        }
        dialog = builder?.create()!!
        dialog.show()
    }
}