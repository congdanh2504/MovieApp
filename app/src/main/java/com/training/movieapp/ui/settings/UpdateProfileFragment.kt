package com.training.movieapp.ui.settings

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import coil.load
import com.training.movieapp.R
import com.training.movieapp.common.LoadingDialog
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentUpdateProfileBinding
import com.training.movieapp.domain.model.state.OperationState
import com.training.movieapp.ui.settings.viewmodel.UpdateProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateProfileFragment : Fragment(R.layout.fragment_update_profile) {

    private val binding: FragmentUpdateProfileBinding by viewBinding(FragmentUpdateProfileBinding::bind)
    private val updateProfileViewModel: UpdateProfileViewModel by viewModels()
    private var imageUri: Uri? = null
    private lateinit var dialog: LoadingDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initActions()
        initObservers()
    }

    private fun initView() {
        dialog = LoadingDialog(requireContext())
    }

    private fun initActions() {
        binding.apply {
            imageViewBack.setOnClickListener {
                findNavController().popBackStack()
            }
            imageViewChoose.setOnClickListener {
                openImageChooser()
            }
            buttonSave.setOnClickListener {
                updateProfile()
            }
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    updateProfileViewModel.user.collect { user ->
                        binding.apply {
                            editTextUsername.text =
                                Editable.Factory.getInstance().newEditable(user.username)
                            editTextBio.text = Editable.Factory.getInstance().newEditable(user.bio)
                            user.imageURL?.let { imageViewChoose.load(user.imageURL) }
                        }
                    }
                }
                launch {
                    updateProfileViewModel.updateProfileState.collect { state ->
                        when (state) {
                            is OperationState.Idle -> {
                                dialog.dismiss()
                                binding.textViewError.isVisible = false
                            }

                            is OperationState.Success -> {
                                dialog.dismiss()
                                binding.textViewError.isVisible = false
                                Toast.makeText(
                                    requireContext(),
                                    "Update profile successfully!",
                                    Toast.LENGTH_LONG
                                ).show()
                                updateProfileViewModel.saveUser(state.data)
                            }

                            is OperationState.Error -> {
                                dialog.dismiss()
                                binding.textViewError.isVisible = true
                                binding.textViewError.text = state.message
                            }

                            is OperationState.Loading -> {
                                dialog.show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun openImageChooser() {
        getContent.launch("image/*")
    }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
            binding.imageViewChoose.setImageURI(uri)
        }

    private fun updateProfile() {
        val username = binding.editTextUsername.text.toString()
        val bio = binding.editTextBio.text.toString()
        updateProfileViewModel.updateProfile(username, bio, imageUri)
    }
}