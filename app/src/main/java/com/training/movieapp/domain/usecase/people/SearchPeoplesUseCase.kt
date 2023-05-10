package com.training.movieapp.domain.usecase.people

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.PageResponse
import com.training.movieapp.domain.model.People
import kotlinx.coroutines.flow.Flow

interface SearchPeoplesUseCase {
    suspend fun execute(query: String, page: Int): Flow<Result<PageResponse<People>>>
}
