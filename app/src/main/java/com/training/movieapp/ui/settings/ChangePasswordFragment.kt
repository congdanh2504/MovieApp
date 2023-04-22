package com.training.movieapp.ui.settings

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.training.movieapp.R
import com.training.movieapp.common.LoadingDialog
import com.training.movieapp.common.setErrorText
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentChangePasswordBinding
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.OperationState
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
        binding.textViewError.visibility = View.INVISIBLE
        dialog = LoadingDialog(requireContext())
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
                    changePasswordViewModel.user.collect {
                        currentUser = it
                    }
                }
                launch {
                    changePasswordViewModel.changePasswordState.collect { state ->
                        when (state) {
                            is OperationState.Success -> {
                                dialog.dismiss()
                                Toast.makeText(
                                    requireContext(),
                                    "Change password successfully!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            is OperationState.Error -> {
                                dialog.dismiss()
                                binding.textViewError.setErrorText(state.message.toString())
                            }

                            is OperationState.Loading -> {
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
            binding.textViewError.setErrorText("Password and confirm password must be the same")
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