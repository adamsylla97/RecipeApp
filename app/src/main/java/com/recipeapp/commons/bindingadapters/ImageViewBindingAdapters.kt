package com.recipeapp.commons.bindingadapters

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("rec:loadImage")
fun ImageView.loadImage(imageUrl: String?) {
    if(imageUrl != null) {
        Glide.with(this.context).load(imageUrl).centerCrop().into(this)
    }
}

@BindingAdapter("rec:src")
fun ImageView.loadBitmap(bitmap: Bitmap?) {
    if(bitmap != null) {
        this.setImageBitmap(bitmap)
    }
}