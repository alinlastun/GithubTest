package com.example.githubtraining.screen.settings

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
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

        mViewModel.getStuffLiveData.observe(this, Observer { it ->
            it?.let {
                stuffDb.owner = it.owner
                stuffDb.collaborator = it.collaborator
                stuffDb.organizationMember = it.organizationMember
            }

        })

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

            stuffDb.idRadioButton = checkedId
            stuffDb.sort = radioGroup.indexOfChild(rb)
            mViewModel.insertStuff(stuffDb)

        }

        alertDialog.show()
    }


   private fun showAffiliationDialog(){
       lateinit var dialog:AlertDialog
       val arrayColors = arrayOf("Owner","Collaborator","Organization Member")
       var myarray = arrayListOf(stuffDb.owner,stuffDb.collaborator,stuffDb.organizationMember)
       val arrayChecked :BooleanArray= myarray.toBooleanArray()
       val builder = AlertDialog.Builder(this)
       builder.setTitle("Affiliation")
       builder.setMultiChoiceItems(arrayColors, arrayChecked) { _, which, isChecked->
           arrayChecked[which] = isChecked
           when(which){
              0->stuffDb.owner=isChecked
              1->stuffDb.collaborator=isChecked
              2->stuffDb.organizationMember=isChecked
           }

       }
       builder.setPositiveButton("OK") { _, _ ->
           mViewModel.insertStuff(stuffDb)
       }
       dialog = builder.create()
       dialog.show()

   }

    override fun onClick(v: View?) {

    }


}
