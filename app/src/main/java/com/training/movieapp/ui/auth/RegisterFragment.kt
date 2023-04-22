package com.training.movieapp.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.training.movieapp.R
import com.training.movieapp.common.LoadingDialog
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentRegisterBinding
import com.training.movieapp.domain.model.state.RegisterState
import com.training.movieapp.ui.auth.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val registerViewModel: RegisterViewModel by viewModels()
    private val binding: FragmentRegisterBinding by viewBinding(FragmentRegisterBinding::bind)
    private lateinit var dialog: LoadingDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initActions()
        initObservers()
    }

    private fun initView() {
        binding.errorTV.visibility = View.INVISIBLE
        dialog = LoadingDialog(requireContext())
    }

    private fun initActions() {
        binding.apply {
            back.setOnClickListener {
                findNavController().popBackStack()
            }
            login.setOnClickListener {
                findNavController().popBackStack()
            }
            createAccountBtn.setOnClickListener {
                register()
            }
        }
    }

    private fun register() {
        val email = binding.emailET.text.toString()
        val username = binding.usernameET.text.toString()
        val password = binding.passwordET.text.toString()
        registerViewModel.register(email, username, password)
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            registerViewModel.registerState
                .collect { state ->
                    when (state) {
                        is RegisterState.Success -> {
                            dialog.dismiss()
                            findNavController().popBackStack()
                        }

                        is RegisterState.Error -> {
                            dialog.dismiss()
                            binding.errorTV.visibility = View.VISIBLE
                            binding.errorTV.text = state.message
                        }

                        is RegisterState.Loading -> {
                            dialog.show()
                        }
                    }
                }
        }
    }

}