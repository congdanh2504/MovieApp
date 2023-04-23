package com.training.movieapp.data.usecase.user

import com.training.movieapp.domain.repository.UserRepository
import com.training.movieapp.domain.usecase.user.CheckUserLoggedInUseCase
import javax.inject.Inject

class CheckUserLoggedInUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    CheckUserLoggedInUseCase {
    override suspend fun execute() = userRepository.checkUserIsLogged()
}