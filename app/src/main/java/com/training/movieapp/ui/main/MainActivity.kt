package com.training.movieapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.training.movieapp.R
import com.training.movieapp.common.Screen
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.ActivityMainBinding
import com.training.movieapp.ui.settings.SettingsActivity


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
                        collapsingToolbar.title = "Movies"
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

        initActions()
        setupDrawerLayout()
    }

    private fun initActions() {
        binding.buttonMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        val headerContainer: View = binding.navigationView.getHeaderView(0)
        val settings = headerContainer.findViewById<ImageView>(R.id.imageView_settings)

        settings.setOnClickListener {
            startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
        }
    }

    private fun setupDrawerLayout() {
        val widthOfNav = (Screen.width) * 0.8
        binding.navigationView.layoutParams.width = widthOfNav.toInt()
        binding.navigationView.requestLayout()
    }
}
