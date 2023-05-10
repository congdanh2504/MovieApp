package com.training.movieapp.data.usecase.people

import com.training.movieapp.domain.repository.PeopleRepository
import com.training.movieapp.domain.usecase.people.SearchPeoplesUseCase
import javax.inject.Inject

class SearchPeoplesUseCaseImpl @Inject constructor(private val peopleRepository: PeopleRepository) :
    SearchPeoplesUseCase {
    override suspend fun execute(query: String, page: Int) =
        peopleRepository.searchPeoples(query, page)
}
