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
import com.training.movieapp.databinding.FragmentChangeEmailBinding
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.settings.viewmodel.ChangeEmailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChangeEmailFragment : Fragment(R.layout.fragment_change_email) {

    private val binding: FragmentChangeEmailBinding by viewBinding(FragmentChangeEmailBinding::bind)
    private val changeEmailViewModel: ChangeEmailViewModel by viewModels()
    private lateinit var currentUser: User
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
                findNavController().popBackStack()
            }
            buttonSave.setOnClickListener {
                changeEmail()
            }
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    changeEmailViewModel.userState.collect { state ->
                        when (state) {
                            is DataState.Success -> {
                                currentUser = state.data
                                binding.textViewCurrentEmail.text = state.data.email
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
                    changeEmailViewModel.changeEmailState.collect { state ->
                        when (state) {
                            is DataState.Idle -> {
                                dialog.dismiss()
                                binding.textViewError.isVisible = false
                            }

                            is DataState.Success -> {
                                dialog.dismiss()
                                binding.textViewError.isVisible = false
                                Toast.makeText(
                                    requireContext(),
                                    "Change email successfully!",
                                    Toast.LENGTH_LONG
                                ).show()
                                changeEmailViewModel.saveUser(state.data)
                            }

                            is DataState.Error -> {
                                dialog.dismiss()
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

    private fun changeEmail() {
        val email = currentUser.email
        val currentPassword = binding.editTextPassword.text.toString()
        val newEmail = binding.editTextNewEmail.text.toString()
        changeEmailViewModel.changeEmail(email, currentPassword, newEmail)
    }
}