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
import android.widget.CheckBox






class SettingsActivity : AppCompatActivity(), View.OnClickListener {


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

    fun onClickAffiliationRepo() {
        showAffiliationDialog()
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

    private fun showAffiliationDialog(){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_affiliation, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        val alertDialog = dialogBuilder.create()

        val checkOwner = dialogView.findViewById<TextView>(R.id.nOwner)
        val checkCollaborator = dialogView.findViewById<TextView>(R.id.nCollaborator)
        val checkOM= dialogView.findViewById<TextView>(R.id.nOrganizationMember)
        checkOwner.setOnClickListener(this)
        checkCollaborator.setOnClickListener(this)
        checkOM.setOnClickListener(this)

        val btnOK = dialogView.findViewById<TextView>(R.id.nOkDialog)
        val btnCancel = dialogView.findViewById<TextView>(R.id.nCancelDialog)
        btnCancel.setOnClickListener { alertDialog.dismiss() }
        alertDialog.show()

    }

    override fun onClick(view: View?) {

       when(view?.id){
           R.id.nOwner->{
               if((view as CheckBox).isChecked){
                   Log.d("wefasdfasd","nOwner")
               }
           }
           R.id.nCollaborator->{
               if((view as CheckBox).isChecked){
                   Log.d("wefasdfasd","nCollaborator")
               }
           }
           R.id.nOrganizationMember->{
               if((view as CheckBox).isChecked){
                   Log.d("wefasdfasd","nOrganizationMember")
               }
           }
       }
    }

}
