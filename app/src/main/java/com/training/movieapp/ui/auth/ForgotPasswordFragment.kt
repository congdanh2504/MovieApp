package com.training.movieapp.ui.auth

import android.app.Dialog
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentForgotPasswordBinding
import com.training.movieapp.domain.model.state.ResetPasswordState
import com.training.movieapp.ui.auth.viewmodel.ResetPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private val resetPasswordViewModel: ResetPasswordViewModel by viewModels()
    private val binding: FragmentForgotPasswordBinding by viewBinding(FragmentForgotPasswordBinding::bind)
    private lateinit var dialog: Dialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initActions()
        initObservers()
    }

    private fun initView() {
        binding.errorTV.visibility = View.INVISIBLE
        dialog = Dialog(requireContext(), R.style.ProgressHUD)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.progress_hud)
        val back =
            dialog.findViewById<ImageView>(R.id.spinnerImageView).background as AnimationDrawable
        back.start()
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
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            resetPasswordViewModel.resetPasswordState
                .collect { state ->
                    when (state) {
                        is ResetPasswordState.EmailSent -> {
                            dialog.dismiss()
                            val action =
                                ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToResetFragment(
                                    binding.emailET.text.toString()
                                )
                            findNavController().navigate(action)
                        }

                        is ResetPasswordState.Error -> {
                            dialog.dismiss()
                            binding.errorTV.visibility = View.VISIBLE
                            binding.errorTV.text = state.message
                        }

                        is ResetPasswordState.Loading -> {
                            dialog.show()
                        }
                    }
                }
        }
    }

}