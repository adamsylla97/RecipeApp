package com.recipeapp.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.recipeapp.BR
import com.recipeapp.R
import com.recipeapp.commons.BindableAdapter

class RecipeCategoriesAdapter(private val onItemClick: (String) -> Unit): RecyclerView.Adapter<RecipeCategoriesAdapter.RecipeHolder>(), BindableAdapter<List<RecipeCategoryItem>> {

    private var categoryItems: List<RecipeCategoryItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.categiry_item, parent, false)
        return RecipeHolder(binding, onItemClick)
    }

    override fun getItemCount(): Int = categoryItems.size

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.bind(categoryItems[position])
    }

    override fun setData(data: List<RecipeCategoryItem>) {
        categoryItems = data
        notifyDataSetChanged()
    }

    class RecipeHolder(val binding: ViewDataBinding, val onItemClick: (String) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipeCategory: RecipeCategoryItem) {
            binding.setVariable(BR.viewModel, recipeCategory)
            binding.executePendingBindings()
            binding.root.rootView.setOnClickListener { onItemClick(recipeCategory.title) }
        }

    }

}