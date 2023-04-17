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
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentMain) as NavHostFragment
        var navController = navHostFragment.navController
        setupWithNavController(binding.bottomNavigation,navController)
        binding.apply {
            collapsingToolbar.title = "Movies"
            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.moviesFragment -> {
                        collapsingToolbar.title = "Movies"
                        navController.navigate(R.id.moviesFragment)
                    }
                    R.id.seriesFragment -> {
                        collapsingToolbar.title = "Series"
                        navController.navigate(R.id.seriesFragment)
                    }
                    R.id.exploreFragment -> {
                        collapsingToolbar.title = "Explore"
                        navController.navigate(R.id.exploreFragment)
                    }
                    R.id.notificationsFragment -> {
                        collapsingToolbar.title = "Notification"
                        navController.navigate(R.id.notificationsFragment)
                    }
                    else -> {
                    }
                }
                true
            }
        }
    }
}
