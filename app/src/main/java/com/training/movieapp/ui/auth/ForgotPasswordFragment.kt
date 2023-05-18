package com.training.movieapp.ui.auth

import android.os.Bundle
import android.view.View
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
import com.training.movieapp.databinding.FragmentForgotPasswordBinding
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.auth.viewmodel.ResetPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private val resetPasswordViewModel: ResetPasswordViewModel by viewModels()
    private val binding: FragmentForgotPasswordBinding by viewBinding(FragmentForgotPasswordBinding::bind)
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
            continueBtn.setOnClickListener {
                resetPasswordViewModel.resetPassword(binding.emailET.text.toString())
            }
            back.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                resetPasswordViewModel.resetPasswordState
                    .collect { state ->
                        when (state) {
                            is DataState.Idle -> {
                                dialog.safeDismiss()
                                binding.errorTV.isVisible = false
                            }

                            is DataState.Success -> {
                                dialog.safeDismiss()
                                binding.errorTV.isVisible = false
                                val action =
                                    ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToResetFragment(
                                        binding.emailET.text.toString()
                                    )
                                findNavController().navigate(action)
                            }

                            is DataState.Error -> {
                                dialog.safeDismiss()
                                binding.errorTV.isVisible = true
                                binding.errorTV.text = state.message
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
