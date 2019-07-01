package com.example.githubtraining.screen.settings

import android.arch.lifecycle.ViewModelProvider
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
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.modelDB.StuffModelDB
import com.example.githubtraining.databinding.ActivitySettingsBinding
import javax.inject.Inject


class SettingsActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {

    }

    @Inject lateinit var factory: ViewModelProvider.Factory
    private lateinit var mViewModel: SettingsViewModel
    private lateinit var mBinding: ActivitySettingsBinding
    private val stuffDb = StuffModelDB()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings)
        mBinding.activity = this
        mViewModel = ViewModelProviders.of(this, factory).get(SettingsViewModel::class.java)

    }

    fun onClickSortRepo() {
        showSortDialog()
    }

    fun onClickAffiliationRepo() {
        //showAffiliationDialog()
        affialtiasd()
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

            stuffDb.idRadioButton = checkedId
            stuffDb.sort = radioGroup.indexOfChild(rb)
            mViewModel.insertIntoStuffDB(stuffDb)

        }

        alertDialog.show()
    }


   fun affialtiasd(){
       lateinit var dialog:AlertDialog
       val arrayColors = arrayOf("Owner","Collaborator","Organization Member")
       var myarray = arrayListOf<Boolean>(stuffDb.owner,stuffDb.collaborator,stuffDb.organizationMember)
       val arrayChecked :BooleanArray= myarray.toBooleanArray()
       val builder = AlertDialog.Builder(this)
       builder.setTitle("Affiliation")
       builder.setMultiChoiceItems(arrayColors, arrayChecked) { _, which, isChecked->
           // Update the clicked item checked status
           arrayChecked[which] = isChecked

           when(which){
              0->stuffDb.owner=isChecked
              1->stuffDb.collaborator=isChecked
              2->stuffDb.organizationMember=isChecked
           }

           Log.d("asdfdasf","  ${which}")
           Log.d("asdfdasf","  ${isChecked}")
       }

       builder.setPositiveButton("OK") { _, _ ->
           mViewModel.insertIntoStuffDB(stuffDb)
           // Do something when click positive button
           val mutableIterator = arrayChecked.iterator()
           //Log.d("asdfdasf","  ${arrayChecked.size}")
           for (i in  0..1) {
             //  Log.d("asdfdasf","  ${mutableIterator.next()}")
           }
       }

       // Initialize the AlertDialog using builder object
       dialog = builder.create()

       // Finally, display the alert dialog
       dialog.show()

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
        btnOK.setOnClickListener {
            mViewModel.insertIntoStuffDB(stuffDb)
            alertDialog.dismiss()
        }

    }



}
