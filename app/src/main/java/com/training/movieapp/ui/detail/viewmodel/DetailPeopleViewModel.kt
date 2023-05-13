package com.training.movieapp.ui.detail.viewmodel

import androidx.lifecycle.viewModelScope
import com.training.movieapp.common.BaseViewModel
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.domain.usecase.people.GetPeopleDetailUseCase
import com.training.movieapp.ui.detail.model.PeopleDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPeopleViewModel @Inject constructor(
    private val getPeopleDetailUseCase: GetPeopleDetailUseCase
) : BaseViewModel() {

    private val _peopleState = MutableStateFlow<DataState<PeopleDetail>>(DataState.Idle)
    val peopleState: StateFlow<DataState<PeopleDetail>> = _peopleState.asStateFlow()

    fun getPeopleDetail(peopleId: Int) = viewModelScope.launch {
        handleState(_peopleState, getPeopleDetailUseCase.execute(peopleId))
    }
}
