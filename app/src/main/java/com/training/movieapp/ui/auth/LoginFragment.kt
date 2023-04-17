package com.training.movieapp.ui.auth

import android.app.Dialog
import android.content.Intent
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
import com.training.movieapp.databinding.FragmentLoginBinding
import com.training.movieapp.domain.model.LoginState
import com.training.movieapp.ui.auth.viewmodel.LoginViewModel
import com.training.movieapp.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val loginViewModel: LoginViewModel by viewModels()
    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
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
        lifecycleScope.launchWhenStarted {
            loginViewModel.loginState.collect { state ->
                when (state) {
                    is LoginState.Success -> {
                        dialog.dismiss()
                        startActivity(Intent(requireActivity(), MainActivity::class.java))
                        requireActivity().finish()
                    }
                    is LoginState.Error -> {
                        dialog.dismiss()
                        binding.errorTV.visibility = View.VISIBLE
                        binding.errorTV.text = state.message
                    }
                    is LoginState.Loading -> {
                        dialog.show()
                    }
                }
            }
        }
    }
}