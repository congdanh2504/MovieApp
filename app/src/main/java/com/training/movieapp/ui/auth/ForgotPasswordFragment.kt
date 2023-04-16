package com.training.movieapp.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.training.movieapp.R
import com.training.movieapp.common.Result
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentForgotPasswordBinding
import com.training.movieapp.ui.auth.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private val authViewModel: AuthViewModel by activityViewModels()
    private val binding: FragmentForgotPasswordBinding by viewBinding(FragmentForgotPasswordBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions()
        initObservers()
    }

    private fun initActions() {
        binding.apply {
            continueBtn.setOnClickListener {
                authViewModel.resetPassword(binding.emailET.text.toString())
            }
            back.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initObservers() {
        authViewModel.resetPasswordState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Result.Success -> {
                    val action =
                        ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToResetFragment(
                            binding.emailET.text.toString()
                        )
                    findNavController().navigate(action)
                }
                is Result.Error -> {
                    binding.errorTV.visibility = View.VISIBLE
                    binding.errorTV.text = state.exception.message
                }
            }
        }
    }

}