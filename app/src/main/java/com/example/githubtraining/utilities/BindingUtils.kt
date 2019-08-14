package com.example.githubtraining.utilities

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.squareup.picasso.Picasso

@BindingAdapter("picture_url")
fun setUrlIntoImage(mImageView: ImageView, mUrl: LiveData<String>) {

        Log.d("ASfasdf", "adfd " + mUrl.value)
        Picasso.get().load(mUrl.value).into(mImageView)
}