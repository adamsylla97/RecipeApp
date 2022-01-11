package com.recipeapp.ui.categories.recipedetails

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.recipeapp.R
import com.recipeapp.databinding.FragmentRecipeDetailsBinding
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf

class RecipeDetailsFragment : Fragment() {

    private val navArgs: RecipeDetailsFragmentArgs by navArgs()
    private val viewModel: RecipeDetailsViewModel by koinNavGraphViewModel(R.id.recipe_details_navigation) {
        parametersOf(navArgs.recipeId)
    }
    private var _binding: FragmentRecipeDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        _binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.instructionsButton.setOnClickListener {
            val recipeName = navArgs.recipeName
            val recipeData = viewModel.recipeData.value
            if(recipeData != null) {
                findNavController().navigate(RecipeDetailsFragmentDirections.toRecipeInstructions(recipeName, recipeData.recipe.url))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val item: MenuItem = menu.add(Menu.NONE, 0, Menu.NONE, "")
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        viewModel.init(item)

        val shareItem: MenuItem = menu.add(Menu.NONE, 1, Menu.NONE, "")
        shareItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        shareItem.setIcon(R.drawable.ic_share)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == 1) {
            viewModel.generateQrCode()
            findNavController().navigate(RecipeDetailsFragmentDirections.showQrCode())
        } else {
            viewModel.onFavoriteButtonClick(item)
        }
        return super.onOptionsItemSelected(item)
    }

}