package com.example.githubtraining.screen.settings

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.githubtraining.R
import com.example.githubtraining.database.modelDB.StuffModelDB
import com.example.githubtraining.utill.LocalViewModelFactory




class SettingsActivity : AppCompatActivity() {

    private lateinit var mViewModel: SettingsViewModel
    private lateinit var mBinding: com.example.githubtraining.databinding.ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings)
        mBinding.activity = this
        mViewModel = ViewModelProviders.of(this, LocalViewModelFactory(this)).get(SettingsViewModel::class.java)



    }

    fun onClickSortRepo() {
        showSortDialog()
    }

    private fun showSortDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_sort, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        val alertDialog = dialogBuilder.create()

        val btnCancel = dialogView.findViewById<TextView>(R.id.nCancelDialog)
        btnCancel?.setOnClickListener { alertDialog.dismiss() }

        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.nRadioGroup)

        if(mViewModel.radioBtnId>0){
            val rb = dialogView.findViewById<RadioButton>(mViewModel.radioBtnId)
            rb.isChecked=true
        }
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val rb = dialogView.findViewById<RadioButton>(checkedId)
            val stuffDb = StuffModelDB()
            stuffDb.idRadioButton = checkedId
            stuffDb.sort = radioGroup.indexOfChild(rb)
            mViewModel.insertIntoStuffDB(stuffDb)

        }

        alertDialog.show()
    }

}
