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
import com.training.movieapp.databinding.FragmentLoginBinding
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.auth.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val loginViewModel: LoginViewModel by viewModels()
    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
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
        val email = binding.emailET.text.toString()
        val password = binding.passwordET.text.toString()
        loginViewModel.login(email, password)
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginState.collect { state ->
                    when (state) {
                        is DataState.Idle -> {
                            dialog.dismiss()
                            binding.errorTV.isVisible = false
                        }

                        is DataState.Success -> {
                            dialog.dismiss()
                            binding.errorTV.isVisible = false
                            loginViewModel.saveUser(state.data)
                            findNavController().navigate(R.id.action_loginFragment_to_moviesFragment)
                        }

                        is DataState.Error -> {
                            dialog.dismiss()
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