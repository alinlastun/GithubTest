package com.example.githubtraining.utill

import android.databinding.BindingAdapter
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["picture_url"], requireAll = false)
fun setUrlIntoImage(mImageView: ImageView, mUrl: String) {

    if (!mUrl.isEmpty() && mUrl.isNotEmpty()) {
        Picasso.get().load(mUrl).into(mImageView)
    }
}