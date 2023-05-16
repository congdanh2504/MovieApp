package com.training.movieapp.ui.detail.viewmodel

import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.BaseViewModel
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.detail.model.series.GetSeriesDetailUseCase
import com.training.movieapp.ui.detail.model.SeriesDetailView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailSeriesViewModel @Inject constructor(private val getSeriesDetailUseCase: GetSeriesDetailUseCase) :
    BaseViewModel() {

    private val _detailSeriesState = MutableStateFlow<DataState<SeriesDetailView>>(DataState.Idle)
    val detailSeriesState = _detailSeriesState.asStateFlow()

    fun getSeriesDetail(seriesId: Int) = viewModelScope.launch {
        handleState(_detailSeriesState, getSeriesDetailUseCase.execute(seriesId))
    }
}