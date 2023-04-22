package com.training.movieapp.ui.settings

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.training.movieapp.R
import com.training.movieapp.common.LoadingDialog
import com.training.movieapp.common.setErrorText
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentUpdateProfileBinding
import com.training.movieapp.domain.model.state.UpdateProfileState
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
        binding.textViewError.visibility = View.INVISIBLE
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
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
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
                        is UpdateProfileState.Success -> {
                            dialog.dismiss()
                            Toast.makeText(
                                requireContext(),
                                "Update profile successfully!",
                                Toast.LENGTH_LONG
                            ).show()
                            updateProfileViewModel.saveUser(state.user)
                        }

                        is UpdateProfileState.Error -> {
                            dialog.dismiss()
                            binding.textViewError.setErrorText(state.message.toString())
                        }

                        is UpdateProfileState.Loading -> {
                            dialog.show()
                        }
                    }
                }
            }

        }
    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            resultLauncher.launch(it)
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result?.data!!.data!!
                imageUri = uri
                binding.imageViewChoose.setImageURI(uri)
            }
        }

    private fun updateProfile() {
        val username = binding.editTextUsername.text.toString()
        val bio = binding.editTextBio.text.toString()
        updateProfileViewModel.updateProfile(username, bio, imageUri)
    }
}