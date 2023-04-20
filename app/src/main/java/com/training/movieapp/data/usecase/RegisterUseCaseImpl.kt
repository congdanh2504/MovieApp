package com.training.movieapp.data.usecase

import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.RegisterUseCase
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) :
    RegisterUseCase {
    override suspend fun register(email: String, username: String, password: String) =
        authRepository.register(email, username, password)
}