package com.training.movieapp.ui.detail.viewmodel

import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.BaseViewModel
import com.training.movieapp.domain.model.CompanyDetail
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.company.GetCompanyDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCompanyViewModel @Inject constructor(private val getCompanyDetailUseCase: GetCompanyDetailUseCase) :
    BaseViewModel() {

    private val _companiesState = MutableStateFlow<DataState<CompanyDetail>>(DataState.Idle)
    val companiesState: StateFlow<DataState<CompanyDetail>> = _companiesState.asStateFlow()

    fun getCompanyDetail(companyId: Int) = viewModelScope.launch {
        handleState(_companiesState, getCompanyDetailUseCase.execute(companyId))
    }
}
