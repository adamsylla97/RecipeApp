package com.recipeapp.commons.bindingadapters

import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.recipeapp.R
import java.io.File

@BindingAdapter("rec:loadImage")
fun ImageView.loadImage(imageUrl: String?) {
    if (imageUrl != null) {
        Glide.with(this.context).load(imageUrl).centerCrop().into(this)
    }
}

@BindingAdapter("rec:src")
fun ImageView.loadBitmap(bitmap: Bitmap?) {
    if (bitmap != null) {
        this.setImageBitmap(bitmap)
    }
}

@BindingAdapter("rec:loadImageFromUri")
fun ImageView.loadImageFromUri(imageUrl: String?) {
    if (imageUrl != null) {
        Glide
            .with(this.context)
            .load(File("$imageUrl"))
            .placeholder(ContextCompat.getDrawable(this.context, R.drawable.ic_placeholder_image))
            .into(this)
//        setImageURI(Uri.parse(imageUrl))
    }
}