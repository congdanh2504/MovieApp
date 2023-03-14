package com.training.movieapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentConfirmEmailBinding

class ConfirmEmailFragment : Fragment() {

    private lateinit var confirmEmailBinding: FragmentConfirmEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        confirmEmailBinding = FragmentConfirmEmailBinding.inflate(layoutInflater, container, false)
        return confirmEmailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions()
    }

    private fun initActions() {
        confirmEmailBinding.apply {
            continueBtn.setOnClickListener {
                findNavController().navigate(R.id.action_confirmEmailFragment2_to_loginFragment)
            }
        }
    }
}