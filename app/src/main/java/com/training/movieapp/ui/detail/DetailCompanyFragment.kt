package com.training.movieapp.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.training.movieapp.R
import com.training.movieapp.common.LoadingDialog
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentDetailCompanyBinding
import com.training.movieapp.domain.model.CompanyDetail
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.detail.viewmodel.DetailCompanyViewModel
import com.training.movieapp.ui.main.utils.Images
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailCompanyFragment : Fragment(R.layout.fragment_detail_company) {

    private val binding: FragmentDetailCompanyBinding by viewBinding(FragmentDetailCompanyBinding::bind)
    private val detailCompanyViewModel: DetailCompanyViewModel by viewModels()
    private val args: DetailCompanyFragmentArgs by navArgs()
    private lateinit var dialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailCompanyViewModel.getCompanyDetail(args.companyId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initActions()
        initObservers()
    }

    private fun initView() {
        dialog = LoadingDialog(childFragmentManager)
    }

    private fun initActions() {
        binding.apply {
            imageViewBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailCompanyViewModel.companiesState.collect { state ->
                    when (state) {
                        is DataState.Idle -> {
                            dialog.safeDismiss()
                        }

                        is DataState.Loading -> {
                            dialog.show()
                        }

                        is DataState.Success -> {
                            dialog.safeDismiss()
                            setCompany(state.data)
                        }

                        is DataState.Error -> {
                            dialog.safeDismiss()
                            Toast.makeText(
                                requireContext(),
                                state.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun setCompany(company: CompanyDetail) {
        binding.apply {
            textViewName.text = company.name
            textViewHomepage.text = company.homepage
            imageViewLogoImage.load(Images.POSTER_BASE_URL + company.logoPath)
            textViewCountry.text = company.originCountry
            textViewHeadquarters.text = company.headquarters
        }
    }
}
