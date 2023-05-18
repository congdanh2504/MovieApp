package com.training.movieapp.ui.settings

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.training.movieapp.R
import com.training.movieapp.common.LoadingDialog
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentChangePasswordBinding
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.settings.viewmodel.ChangePasswordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChangePasswordFragment : Fragment(R.layout.fragment_change_password) {
    private val binding: FragmentChangePasswordBinding by viewBinding(FragmentChangePasswordBinding::bind)
    private val changePasswordViewModel: ChangePasswordViewModel by viewModels()
    private lateinit var currentUser: User
    private lateinit var dialog: LoadingDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initActions()
        initObservers()
    }

    private fun initView() {
        dialog = LoadingDialog(childFragmentManager)
    }

    private fun initActions() {
        binding.apply {
            imageViewBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonSave.setOnClickListener {
                if (validate()) {
                    changePassword()
                }
            }
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    changePasswordViewModel.userState.collect { state ->
                        when (state) {
                            is DataState.Success -> {
                                currentUser = state.data
                            }

                            is DataState.Error -> {
                                Toast.makeText(
                                    requireContext(),
                                    state.message.toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            else -> {}
                        }
                    }
                }
                launch {
                    changePasswordViewModel.changePasswordState.collect { state ->
                        when (state) {
                            is DataState.Idle -> {
                                dialog.safeDismiss()
                                binding.textViewError.isVisible = false
                            }

                            is DataState.Success -> {
                                dialog.safeDismiss()
                                binding.textViewError.isVisible = false
                                Toast.makeText(
                                    requireContext(),
                                    "Change password successfully!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            is DataState.Error -> {
                                dialog.safeDismiss()
                                binding.textViewError.isVisible = true
                                binding.textViewError.text = state.message
                            }

                            is DataState.Loading -> {
                                dialog.show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun validate(): Boolean {
        val password = binding.editTextNewPassword.text.toString()
        val confirmPassword = binding.editTextConfirmPassword.text.toString()
        if (password != confirmPassword) {
            binding.textViewError.isVisible = true
            binding.textViewError.text = "Password and confirm password must be the same"
            return false
        } else {
            binding.textViewError.visibility = View.GONE
        }
        return true
    }

    private fun changePassword() {
        val email = currentUser.email
        val currentPassword = binding.editTextCurrentPassword.text.toString()
        val newPassword = binding.editTextNewPassword.text.toString()
        changePasswordViewModel.changePassword(email, currentPassword, newPassword)
    }
}
