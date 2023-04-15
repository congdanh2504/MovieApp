package com.training.movieapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentResetBinding

class ResetFragment : Fragment() {

    private lateinit var resetBinding: FragmentResetBinding
    private val args: ConfirmEmailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        resetBinding = FragmentResetBinding.inflate(layoutInflater, container, false)
        return resetBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resetBinding.descriptionTV.text = resetBinding.descriptionTV.text.toString().replace("emailAddress", args.email)
        initActions()
    }

    private fun initActions() {
        resetBinding.apply {
            cancelBtn.setOnClickListener {
                findNavController().popBackStack()
            }
            loginBtn.setOnClickListener {
                findNavController().navigate(R.id.action_resetFragment_to_loginFragment)
            }
        }
    }
}