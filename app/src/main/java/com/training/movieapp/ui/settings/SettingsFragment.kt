package com.training.movieapp.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import coil.load
import com.training.movieapp.R
import com.training.movieapp.common.LoadingDialog
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentSettingsBinding
import com.training.movieapp.domain.model.state.OperationState
import com.training.movieapp.ui.auth.AuthActivity
import com.training.movieapp.ui.main.MainActivity
import com.training.movieapp.ui.settings.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)
    private val settingsViewModel: SettingsViewModel by viewModels()
    private lateinit var dialog: LoadingDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initActions()
        initObservers()
    }

    private fun initView() {
        dialog = LoadingDialog(requireContext())
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
            linearLayoutChangeEmail.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_changeEmailFragment)
            }
            cardViewSignOut.setOnClickListener {
                settingsViewModel.signOut()
            }
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    settingsViewModel.signOutState.collect { state ->
                        when (state) {
                            is OperationState.Idle -> {
                                dialog.dismiss()
                            }

                            is OperationState.Success -> {
                                dialog.dismiss()
                                startActivity(Intent(requireContext(), AuthActivity::class.java))
                                requireActivity().finish()
                            }

                            is OperationState.Error -> {
                                dialog.dismiss()
                                Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG)
                                    .show()
                            }

                            is OperationState.Loading -> {
                                dialog.show()
                            }
                        }
                    }
                }
                launch {
                    settingsViewModel.user.collect { user ->
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
}