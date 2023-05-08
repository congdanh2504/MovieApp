package com.training.movieapp.data.usecase.people

import com.training.movieapp.domain.repository.PeopleRepository
import com.training.movieapp.domain.usecase.people.GetPeopleDetailUseCase
import javax.inject.Inject

class GetPeopleDetailUseCaseImpl @Inject constructor(private val peopleRepository: PeopleRepository) :
    GetPeopleDetailUseCase {
    override suspend fun execute(peopleId: Int) = peopleRepository.getPeopleDetail(peopleId)
}