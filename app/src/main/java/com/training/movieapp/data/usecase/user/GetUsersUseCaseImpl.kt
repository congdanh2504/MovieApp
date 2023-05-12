package com.training.movieapp.data.usecase.user

import com.training.movieapp.domain.repository.UserRepository
import com.training.movieapp.domain.usecase.user.GetUsersUseCase
import javax.inject.Inject

class GetUsersUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    GetUsersUseCase {
    override suspend fun execute() = userRepository.getUsers()
}
