package com.training.movieapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentMain) as NavHostFragment
        val navController = navHostFragment.navController
        setupWithNavController(binding.bottomNavigation, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.apply {
                when (destination.id) {
                    R.id.moviesFragment -> {
                        binding.collapsingToolbar.title = "Movies"
                    }
                    R.id.seriesFragment -> {
                        collapsingToolbar.title = "Series"
                    }
                    R.id.exploreFragment -> {
                        collapsingToolbar.title = "Explore"
                    }
                    R.id.notificationsFragment -> {
                        collapsingToolbar.title = "Notification"
                    }
                }
            }
        }
    }
}
