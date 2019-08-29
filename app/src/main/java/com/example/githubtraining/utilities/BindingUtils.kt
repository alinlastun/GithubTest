package com.example.githubtraining.utilities

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("picture_url")
fun setUrlIntoImage(mImageView: ImageView, mUrl: String?) {
        if(mUrl!=null && mUrl.isNotEmpty()){
                Picasso.get().load(mUrl).into(mImageView)
        }

}