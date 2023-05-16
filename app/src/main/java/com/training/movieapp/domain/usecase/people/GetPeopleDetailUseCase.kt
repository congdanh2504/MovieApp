package com.training.movieapp.domain.usecase.people

import com.training.movieapp.common.Result
import com.training.movieapp.ui.detail.model.PeopleDetailView
import kotlinx.coroutines.flow.Flow

interface GetPeopleDetailUseCase {
    suspend fun execute(peopleId: Int): Flow<Result<PeopleDetailView>>
}
