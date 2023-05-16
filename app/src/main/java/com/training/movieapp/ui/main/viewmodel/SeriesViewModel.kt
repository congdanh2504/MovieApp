package com.training.movieapp.ui.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.BaseViewModel
import com.training.movieapp.domain.model.TopSeries
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.detail.model.series.GetSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(private val getSeriesUseCase: GetSeriesUseCase) :
    BaseViewModel() {
    private val _seriesState = MutableStateFlow<DataState<TopSeries>>(DataState.Idle)
    val mainSeriesState: StateFlow<DataState<TopSeries>> = _seriesState.asStateFlow()

    fun getSeries() = viewModelScope.launch {
        handleState(_seriesState, getSeriesUseCase.execute())
    }
}
