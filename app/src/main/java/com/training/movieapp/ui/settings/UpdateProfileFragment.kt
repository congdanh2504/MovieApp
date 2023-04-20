package com.training.movieapp.ui.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentUpdateProfileBinding
import com.training.movieapp.ui.settings.viewmodel.UpdateProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateProfileFragment : Fragment(R.layout.fragment_update_profile) {

    private val binding: FragmentUpdateProfileBinding by viewBinding(FragmentUpdateProfileBinding::bind)
    private val updateProfileViewModel: UpdateProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions()
        initObservers()
    }

    private fun initActions() {
        binding.apply {
            imageViewBack.setOnClickListener {
                findNavController().popBackStack()
            }
            imageViewChoose.setOnClickListener {
                openImageChooser()
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            updateProfileViewModel.user.collect() { user ->
                binding.apply {
                    editTextUsername.text = Editable.Factory.getInstance().newEditable(user.username)
                    editTextBio.text = Editable.Factory.getInstance().newEditable(user.bio)
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
                binding.imageViewChoose.setImageURI(uri)
            }
        }
}