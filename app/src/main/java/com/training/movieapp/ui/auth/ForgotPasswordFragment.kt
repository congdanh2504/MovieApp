package com.training.movieapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment() {

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
    }

    private fun initActions() {
        forgotPasswordBinding.apply {
            continueBtn.setOnClickListener {
                findNavController().navigate(R.id.action_forgotPasswordFragment_to_resetFragment)
            }
            back.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}