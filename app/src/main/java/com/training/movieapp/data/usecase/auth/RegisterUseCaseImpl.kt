package com.training.movieapp.data.usecase.auth

import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.auth.RegisterUseCase
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) :
    RegisterUseCase {
    override suspend fun execute(email: String, username: String, password: String) =
        authRepository.register(email, username, password)
}