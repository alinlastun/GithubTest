package com.example.githubtraining.utill.loading

import android.content.Context
import com.example.githubtraining.R

class Loading(mContext: Context) : BaseDialog(mContext) {

    fun refresh(): Loading {
        createView(R.layout.custom_dialog_loading)
        mDialog.setCancelable(false)
        return this
    }

    fun showLoading(nValue:Boolean): Loading {
        if(nValue) showDialog() else dismissDialog()
        return this
    }
}