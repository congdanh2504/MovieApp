package com.training.movieapp.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentResetBinding

class ResetFragment : Fragment(R.layout.fragment_reset) {

    private val binding: FragmentResetBinding by viewBinding(FragmentResetBinding::bind)
    private val args: ResetFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.descriptionTV.text =
            binding.descriptionTV.text.toString().replace("emailAddress", args.email)
        initActions()
    }

    private fun initActions() {
        binding.apply {
            cancelBtn.setOnClickListener {
                findNavController().popBackStack()
            }
            loginBtn.setOnClickListener {
                findNavController().navigate(R.id.action_resetFragment_to_loginFragment)
            }
        }
    }
}
