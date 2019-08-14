package com.example.githubtraining.utilities.loading

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout

open class BaseDialog(private val nContext: Context?) {

    private lateinit var mContainer : ConstraintLayout
    private lateinit var mBuilder: AlertDialog.Builder
    lateinit var mDialog: AlertDialog
    private var mIsPopUpReady = false

    protected fun createView(nCustomLayout: Int) {
        mContainer = LayoutInflater.from(nContext).inflate(nCustomLayout, null) as ConstraintLayout
        create()
    }

    private fun create() {
        mBuilder = nContext?.let { AlertDialog.Builder(it).setView(mContainer) }!!
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

}