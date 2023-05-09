package com.training.movieapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.Series
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.series.GetSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(private val getSeriesUseCase: GetSeriesUseCase) :
    ViewModel() {
    private val _seriesState = MutableStateFlow<DataState<Series>>(DataState.Idle)
    val seriesState: StateFlow<DataState<Series>> = _seriesState.asStateFlow()
    fun getSeries() = viewModelScope.launch {
        getSeriesUseCase.execute()
            .onStart { _seriesState.emit(DataState.Loading) }
            .collect { result ->
                when (result) {
                    is Result.Success -> {
                        _seriesState.emit(DataState.Success(result.data))
                    }

                    is Result.Error -> {
                        _seriesState.emit(DataState.Error(result.exception.message))
                    }
                }
            }
    }
}
