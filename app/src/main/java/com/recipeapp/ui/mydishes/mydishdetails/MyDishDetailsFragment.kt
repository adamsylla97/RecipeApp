package com.recipeapp.ui.mydishes.mydishdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.recipeapp.R
import com.recipeapp.databinding.FragmentMyDishDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MyDishDetailsFragment : Fragment() {

    private val navArgs: MyDishDetailsFragmentArgs by navArgs()
    private val viewModel: MyDishDetailsViewModel by viewModel { parametersOf(navArgs.dishId) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentMyDishDetailsBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }.root
    }

}