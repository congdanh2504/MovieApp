package com.training.movieapp.domain.repository

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.PageResponse
import com.training.movieapp.domain.model.People
import com.training.movieapp.ui.detail.model.PeopleDetail
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    suspend fun searchPeoples(query: String, page: Int): Flow<Result<PageResponse<People>>>

    suspend fun getPeopleDetail(peopleId: Int): Flow<Result<PeopleDetail>>
}
