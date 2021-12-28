package com.recipeapp.commons.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("rec:loadImage")
fun ImageView.loadImage(imageUrl: String?) {
    if(imageUrl != null) {
        Glide.with(this.context).load(imageUrl).centerCrop().into(this)
    }
}