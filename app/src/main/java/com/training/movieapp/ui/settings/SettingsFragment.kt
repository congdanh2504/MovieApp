package com.training.movieapp.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentSettingsBinding
import com.training.movieapp.domain.model.SignOutState
import com.training.movieapp.ui.auth.AuthActivity
import com.training.movieapp.ui.main.MainActivity
import com.training.movieapp.ui.settings.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions()
        initObservers()
    }

    private fun initActions() {
        binding.apply {
            imageViewBack.setOnClickListener {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }
            cardViewProfile.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_updateProfileFragment)
            }
            linearLayoutChangePassword.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_changePasswordFragment)
            }
            cardViewSignOut.setOnClickListener {
                settingsViewModel.signOut()
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            settingsViewModel.signOutState.collect() { state ->
                when (state) {
                    is SignOutState.Success -> {
                        startActivity(Intent(requireContext(), AuthActivity::class.java))
                    }
                    is SignOutState.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}