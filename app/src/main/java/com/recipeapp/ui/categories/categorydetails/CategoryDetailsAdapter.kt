package com.recipeapp.ui.categories.categorydetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.recipeapp.BR
import com.recipeapp.R
import com.recipeapp.commons.BindableAdapter

class CategoryDetailsAdapter(private val onItemClick: (String, String) -> Unit): RecyclerView.Adapter<CategoryDetailsAdapter.CategoryDetailsHolder>(), BindableAdapter<List<RecipeItem>> {

    private var items: List<RecipeItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.recipe_item, parent, false)
        return CategoryDetailsHolder(binding, onItemClick)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CategoryDetailsHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun setData(data: List<RecipeItem>) {
        items = data
        notifyDataSetChanged()
    }

    class CategoryDetailsHolder(val binding: ViewDataBinding, private val onItemClick: (String, String) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeItem) {
            binding.setVariable(BR.viewModel, item)
            binding.executePendingBindings()
            binding.root.rootView.setOnClickListener { onItemClick(item.title, item.itemId) }
        }

    }

}