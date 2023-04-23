package com.training.movieapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.auth.FirebaseAuth
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.ActivityAuthBinding
import com.training.movieapp.ui.auth.viewmodel.AuthViewModel
import com.training.movieapp.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private val binding: ActivityAuthBinding by viewBinding(ActivityAuthBinding::inflate)
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var splashScreen: SplashScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        splashScreen.setKeepOnScreenCondition {
            true
        }
        authViewModel.checkUserLoggedIn()
        initObservers()
    }

    private fun initObservers() {
        authViewModel.isUserLoggedIn.observe(this) { isUserLoggedIn ->
            if (isUserLoggedIn) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                splashScreen.setKeepOnScreenCondition {
                    false
                }
            }
        }
    }

}