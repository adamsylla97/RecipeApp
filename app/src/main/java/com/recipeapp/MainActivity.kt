package com.recipeapp

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.recipeapp.databinding.ActivityMainBinding
import com.recipeapp.ui.NoBottomNavigationViewDestinations

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigation()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (NoBottomNavigationViewDestinations.get().contains(destination.id)) {
                hideBottomNavigationView()
            } else {
                showBottomNavigationView()
            }
        }
    }

    private fun hideBottomNavigationView() {
        binding.navView.visibility = View.GONE
    }

    private fun showBottomNavigationView() {
        binding.navView.visibility = View.VISIBLE
    }

    private fun setUpNavigation() {
        val navView: BottomNavigationView = binding.navView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        navView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(getTopLevelDestinations())
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    private fun getTopLevelDestinations() = setOf(R.id.favoritesScreen, R.id.categoriesFragment, R.id.myDishes)
}