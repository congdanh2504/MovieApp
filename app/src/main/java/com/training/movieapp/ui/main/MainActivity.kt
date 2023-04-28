package com.training.movieapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import coil.load
import com.training.movieapp.R
import com.training.movieapp.common.getWidth
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.ActivityMainBinding
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.main.viewmodel.MainViewModel
import com.training.movieapp.ui.settings.SettingsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initNavController()
        initActions()
        initObservers()
        setupDrawerLayout()
    }

    private fun initNavController() {
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

    private fun initObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.userState.collect() { state ->
                    when (state) {
                        is DataState.Success -> {
                            setUser(state.data)
                        }

                        is DataState.Error -> {
                            Toast.makeText(
                                this@MainActivity,
                                state.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun setupDrawerLayout() {
        val widthOfNav = applicationContext.getWidth() * 0.8
        binding.navigationView.layoutParams.width = widthOfNav.toInt()
        binding.navigationView.requestLayout()
    }

    private fun setUser(user: User) {
        val headerContainer: View = binding.navigationView.getHeaderView(0)
        val usernameTextView =
            headerContainer.findViewById<TextView>(R.id.textView_username)
        val username2TextView =
            headerContainer.findViewById<TextView>(R.id.textView_username2)
        val userImage =
            headerContainer.findViewById<ImageView>(R.id.imageView_userImage)
        usernameTextView.text = user.username
        username2TextView.text = "@${user.username}"
        user.imageURL?.let { userImage.load(user.imageURL) }
    }
}
