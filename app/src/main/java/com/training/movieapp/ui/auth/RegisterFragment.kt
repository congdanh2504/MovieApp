package com.training.movieapp.ui.auth

import android.app.Dialog
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentRegisterBinding
import com.training.movieapp.domain.model.AuthState
import com.training.movieapp.ui.auth.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val authViewModel: AuthViewModel by activityViewModels()
    private lateinit var registerBinding: FragmentRegisterBinding
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registerBinding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return registerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initActions()
        initObservers()
    }

    private fun initView() {
        registerBinding.errorTV.visibility = View.INVISIBLE
        dialog = Dialog(requireContext(), R.style.ProgressHUD)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.progress_hud)
        val back = dialog.findViewById<ImageView>(R.id.spinnerImageView).background as AnimationDrawable
        back.start()
    }

    private fun initActions() {
        registerBinding.apply {
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
        val email = registerBinding.emailET.text.toString()
        val username = registerBinding.usernameET.text.toString()
        val password = registerBinding.passwordET.text.toString()
        authViewModel.register(email, username, password)
    }

    private fun initObservers() {
        authViewModel.authState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AuthState.Authenticated -> {
                    dialog.dismiss()
                    findNavController().navigate(R.id.action_registerFragment_to_confirmEmailFragment2)
                }
                is AuthState.UnAuthenticated -> {
                    dialog.dismiss()
                }
                is AuthState.Error -> {
                    dialog.dismiss()
                    registerBinding.errorTV.visibility = View.VISIBLE
                    registerBinding.errorTV.text = state.message
                }
                AuthState.Loading -> {
                    dialog.show()
                }
            }
        }
    }

}