package com.training.movieapp.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.Coil
import coil.load
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentSettingsBinding
import com.training.movieapp.domain.model.state.SignOutState
import com.training.movieapp.ui.auth.AuthActivity
import com.training.movieapp.ui.main.MainActivity
import com.training.movieapp.ui.settings.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        settingsViewModel.readUser()
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
            launch {
                settingsViewModel.signOutState.collect() { state ->
                    when (state) {
                        is SignOutState.Success -> {
                            startActivity(Intent(requireContext(), AuthActivity::class.java))
                            requireActivity().finish()
                        }
                        is SignOutState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
            launch {
                settingsViewModel.user.collect() { user ->
                    binding.apply {
                        textViewUsername.text = user.username
                        textViewUsername2.text = "@${user.username}"
                        user.imageURL?.let { imageViewUserImage.load(user.imageURL) }
                    }
                }
            }
        }
    }
}