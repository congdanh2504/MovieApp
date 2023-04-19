package com.training.movieapp.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.ActivitySettingsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {

    private val binding: ActivitySettingsBinding by viewBinding(ActivitySettingsBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}