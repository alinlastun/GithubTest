package com.example.githubtraining.utill.loading

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View

open class BaseDialog(private val nContext:Context): View.OnClickListener {

    private lateinit var mContainer : ConstraintLayout
    private lateinit var mBuilder: AlertDialog.Builder
    lateinit var mDialog: AlertDialog
    private var mIsPopUpReady = false

    protected fun createView(nCustomLayout: Int) {
        mContainer = LayoutInflater.from(nContext).inflate(nCustomLayout,null) as ConstraintLayout
        create()

    }

    private fun create() {
        mBuilder = AlertDialog.Builder(nContext).setView(mContainer)
        mDialog = mBuilder.create()
        mIsPopUpReady = true
    }

    fun showDialog() {
        if (mIsPopUpReady && !mDialog.isShowing)  mDialog.show()
    }

    fun dismissDialog() {
        if (mIsPopUpReady && mDialog.isShowing)
            mDialog.dismiss()
    }


    override fun onClick(v: View?) {
    }
}