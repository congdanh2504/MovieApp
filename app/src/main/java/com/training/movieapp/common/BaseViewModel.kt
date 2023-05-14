package com.training.movieapp.common

import androidx.lifecycle.ViewModel
import com.training.movieapp.domain.model.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart

abstract class BaseViewModel : ViewModel() {

    protected suspend fun <T : Any> handleState(
        stateFlow: MutableStateFlow<DataState<T>>,
        flow: Flow<Result<T>>,
    ) = flow.onStart { stateFlow.emit(DataState.Loading) }
        .collect { result ->
            when (result) {
                is Result.Success -> {
                    stateFlow.emit(DataState.Success(result.data))
                }

                is Result.Error -> {
                    stateFlow.emit(DataState.Error(result.exception.message))
                }
            }
        }
}
