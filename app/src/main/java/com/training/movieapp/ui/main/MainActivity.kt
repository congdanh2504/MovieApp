package com.training.movieapp.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
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
import com.training.movieapp.ui.main.viewmodel.MoviesViewModel
import com.training.movieapp.ui.main.viewmodel.SeriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    private val mainViewModel: MainViewModel by viewModels()
    private val moviesViewModel: MoviesViewModel by viewModels()
    private val seriesViewModel: SeriesViewModel by viewModels()
    private lateinit var splashScreen: SplashScreen
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initNavController()
        initActions()
        initObservers()
        setupDrawerLayout()

        mainViewModel.checkUserLoggedIn()
        moviesViewModel.getMovies()
        seriesViewModel.getSeries()
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentMain) as NavHostFragment
        navController = navHostFragment.navController
        setupWithNavController(binding.bottomNavigation, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val title = when (destination.id) {
                R.id.moviesFragment -> "Movies"
                R.id.seriesFragment -> "Series"
                R.id.exploreFragment -> "Explore"
                R.id.notificationsFragment -> "Notification"
                else -> null
            }

            title?.let { binding.collapsingToolbar.title = it }
            setAppbarAndBottomNavigation(isVisible = title != null)
        }
    }

    private fun initActions() {
        binding.toolBar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.toolBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.search -> {
                    navController.navigateUp()
                    navController.navigate(R.id.searchFragment)
                }
            }
            true
        }

        val headerContainer: View = binding.navigationView.getHeaderView(0)
        val settings = headerContainer.findViewById<ImageView>(R.id.imageView_settings)

        settings.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            navController.navigateUp()
            val navOptions = NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .build()

            navController.navigate(R.id.settingsFragment, null, navOptions)
        }
    }

    private fun initObservers() {
        mainViewModel.isUserLoggedIn.observe(this) { isUserLoggedIn ->
            splashScreen.setKeepOnScreenCondition { false }
            if (!isUserLoggedIn) {
                navController.navigate(R.id.action_moviesFragment_to_loginFragment)
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.userState.collect() { state ->
                    when (state) {
                        is DataState.Success -> setUser(state.data)

                        is DataState.Error -> Toast.makeText(
                            this@MainActivity,
                            state.message.toString(),
                            Toast.LENGTH_LONG
                        ).show()

                        else -> {}
                    }
                }
            }
        }
    }

    private fun setupDrawerLayout() {
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        binding.navigationView.layoutParams.width = (applicationContext.getWidth() * 0.8).toInt()
        binding.navigationView.requestLayout()
    }

    private fun setAppbarAndBottomNavigation(isVisible: Boolean) {
        binding.apply {
            appBar.isVisible = isVisible
            bottomNavigation.isVisible = isVisible
        }
    }

    private fun setUser(user: User) {
        val headerView = binding.navigationView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.textView_username).text = user.username
        headerView.findViewById<TextView>(R.id.textView_username2).text = "@${user.username}"
        headerView.findViewById<ImageView>(R.id.imageView_userImage)
            .load(user.imageURL ?: R.drawable.icons8user)
    }
}
