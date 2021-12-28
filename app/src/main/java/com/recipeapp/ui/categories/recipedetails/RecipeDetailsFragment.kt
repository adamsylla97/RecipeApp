package com.recipeapp.ui.categories.recipedetails

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.recipeapp.R
import com.recipeapp.databinding.FragmentRecipeDetailsBinding
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RecipeDetailsFragment : Fragment() {

    private val navArgs: RecipeDetailsFragmentArgs by navArgs()
    private val viewModel: RecipeDetailsViewModel by viewModel{ parametersOf(navArgs.recipeId) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return FragmentRecipeDetailsBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val item: MenuItem = menu.add(Menu.NONE, 0, Menu.NONE, "")
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        viewModel.init(item)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.onFavoriteButtonClick(item)
        return super.onOptionsItemSelected(item)
    }

}