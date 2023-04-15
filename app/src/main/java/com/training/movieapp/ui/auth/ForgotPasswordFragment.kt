package com.training.movieapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.training.movieapp.domain.model.Result
import com.training.movieapp.databinding.FragmentForgotPasswordBinding
import com.training.movieapp.ui.auth.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {

    private val authViewModel: AuthViewModel by activityViewModels()
    private lateinit var forgotPasswordBinding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        forgotPasswordBinding =
            FragmentForgotPasswordBinding.inflate(layoutInflater, container, false)
        return forgotPasswordBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions()
        initObservers()
    }

    private fun initActions() {
        forgotPasswordBinding.apply {
            continueBtn.setOnClickListener {
                authViewModel.resetPassword(forgotPasswordBinding.emailET.text.toString())
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
                            forgotPasswordBinding.emailET.text.toString()
                        )
                    findNavController().navigate(action)
                }
                is Result.Error -> {
                    forgotPasswordBinding.errorTV.visibility = View.VISIBLE
                    forgotPasswordBinding.errorTV.text = state.exception.message
                }
            }
        }
    }

}