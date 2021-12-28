package com.recipeapp.commons.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.recipeapp.commons.BindableAdapter

@BindingAdapter("app:data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T?) {
    if(data != null) {
        if (recyclerView.adapter is BindableAdapter<*>) {
            (recyclerView.adapter as BindableAdapter<T>).setData(data)
        }
    }
}