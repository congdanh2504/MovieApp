package com.training.movieapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentLoginBinding
import com.training.movieapp.domain.model.AuthState
import com.training.movieapp.ui.auth.viewmodel.AuthViewModel
import com.training.movieapp.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val authViewModel: AuthViewModel by activityViewModels()
    private lateinit var loginBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBinding.errorTV.visibility = View.INVISIBLE
        initActions()
        initObservers()
    }

    private fun initActions() {
        loginBinding.apply {
            createAccount.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            forgotPassword.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
            }
            loginBtn.setOnClickListener {
                login()
            }
        }
    }

    private fun login() {
        val email = loginBinding.emailET.text.toString()
        val password = loginBinding.passwordET.text.toString()
        authViewModel.login(email, password)
    }

    private fun initObservers() {
        authViewModel.authState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AuthState.Authenticated -> {
                    startActivity(Intent(requireActivity(), MainActivity::class.java))
                    requireActivity().finish()
                }
                is AuthState.Error -> {
                    loginBinding.errorTV.visibility = View.VISIBLE
                    loginBinding.errorTV.text = state.message
                }
                AuthState.Loading -> {
                    // Show a progress indicator
                }
            }
        }
    }

}