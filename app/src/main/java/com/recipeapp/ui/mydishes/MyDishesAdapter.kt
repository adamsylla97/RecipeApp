package com.recipeapp.ui.mydishes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.recipeapp.BR
import com.recipeapp.R
import com.recipeapp.commons.BindableAdapter

class MyDishesAdapter(private val onClick: (String, Int) -> Unit): RecyclerView.Adapter<MyDishesAdapter.MyDishHolder>(), BindableAdapter<List<MyDish>> {

    private var myDishesItems: List<MyDish> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDishHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.mydish_item, parent, false)
        return MyDishHolder(binding, onClick)
    }

    override fun getItemCount(): Int = myDishesItems.size

    override fun onBindViewHolder(holder: MyDishHolder, position: Int) {
        holder.bind(myDishesItems[position])
    }

    override fun setData(data: List<MyDish>) {
        myDishesItems = data
        notifyDataSetChanged()
    }

    class MyDishHolder(val binding: ViewDataBinding, private val onClick: (String, Int) -> Unit): RecyclerView.ViewHolder(binding.root) {

        fun bind(myDish: MyDish) {
            binding.setVariable(BR.viewModel, myDish)
            binding.executePendingBindings()
            binding.root.rootView.setOnClickListener { onClick(myDish.name, myDish.id) }
        }

    }

}