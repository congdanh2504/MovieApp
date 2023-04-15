package com.training.movieapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.auth.FirebaseAuth
import com.training.movieapp.R
import com.training.movieapp.ui.auth.viewmodel.AuthViewModel
import com.training.movieapp.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    @Inject lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
//        firebaseAuth.signOut()
        splashScreen.setKeepOnScreenCondition {
            true
        }
        if (firebaseAuth.currentUser == null) {
            splashScreen.setKeepOnScreenCondition {
                false
            }
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}