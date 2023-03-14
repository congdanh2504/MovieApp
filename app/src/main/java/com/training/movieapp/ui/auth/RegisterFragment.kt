package com.training.movieapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var registerBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registerBinding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return registerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions()
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
                findNavController().navigate(R.id.action_registerFragment_to_confirmEmailFragment2)
            }
        }
    }

}