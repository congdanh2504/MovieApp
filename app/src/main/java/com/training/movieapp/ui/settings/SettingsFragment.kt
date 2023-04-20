package com.training.movieapp.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentSettingsBinding
import com.training.movieapp.ui.main.MainActivity

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions()
    }

    private fun initActions() {
        binding.apply {
            imageViewBack.setOnClickListener {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }
            cardViewProfile.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_updateProfileFragment)
            }
            linearLayoutChangePassword.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_changePasswordFragment)
            }
        }
    }

}