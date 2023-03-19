package com.training.movieapp.ui.main


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.training.movieapp.R
import com.training.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(MoviesFragment())
        binding.apply {
            collapsingToolbar.title = "Movies"
            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.action_movies -> {
                        replaceFragment(MoviesFragment())
                        collapsingToolbar.title = "Movies"
                    }
                    R.id.action_series -> {
                        replaceFragment(SeriesFragment())
                        collapsingToolbar.title = "Series"
                    }
                    R.id.action_explore ->{
                        replaceFragment(ExploreFragment())
                        collapsingToolbar.title = "Explore"
                    }
                    R.id.action_notification -> {
                        replaceFragment(NotificationsFragment())
                        collapsingToolbar.title = "Notification"
                    }
                    else -> {

                    }
                }
                true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
