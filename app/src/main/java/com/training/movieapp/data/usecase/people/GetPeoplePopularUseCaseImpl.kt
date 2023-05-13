package com.training.movieapp.data.usecase.people

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.PageResponse
import com.training.movieapp.domain.model.People
import com.training.movieapp.domain.repository.PeopleRepository
import com.training.movieapp.domain.usecase.people.GetPeoplePopularUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeoplePopularUseCaseImpl@Inject constructor(private val peopleRepository: PeopleRepository) :
    GetPeoplePopularUseCase {
    override suspend fun execute(): Flow<Result<PageResponse<People>>> {
        return peopleRepository.getPeoplePopular()
    }

}