package com.training.movieapp.data.usecase

import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val authRepository: AuthRepository): LoginUseCase {
    override suspend fun login(email: String, password: String) = authRepository.login(email, password)
}