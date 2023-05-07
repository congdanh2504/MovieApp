package com.training.movieapp.domain.repository

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.PageResponse
import com.training.movieapp.domain.model.People
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    suspend fun searchPeoples(query: String, page: Int): Flow<Result<PageResponse<People>>>
}