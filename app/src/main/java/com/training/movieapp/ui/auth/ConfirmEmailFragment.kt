package com.training.movieapp.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentConfirmEmailBinding
import com.training.movieapp.ui.auth.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmEmailFragment : Fragment(R.layout.fragment_confirm_email) {

    private val authViewModel: AuthViewModel by activityViewModels()
    private val binding: FragmentConfirmEmailBinding by viewBinding(FragmentConfirmEmailBinding::bind)
    private val args: ConfirmEmailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.descriptionTV.text =
            binding.descriptionTV.text.toString().replace("emailAddress", args.email)
        initActions()
    }

    private fun initActions() {
        binding.apply {
            continueBtn.setOnClickListener {
                findNavController().navigate(R.id.action_confirmEmailFragment2_to_loginFragment)
            }
            signOutBtn.setOnClickListener {
                authViewModel.signOut()
                findNavController().navigate(R.id.action_confirmEmailFragment2_to_loginFragment)
            }
        }
    }
}