package com.training.movieapp.data.usecase.auth

import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.auth.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val authRepository: AuthRepository):
    LoginUseCase {
    override suspend fun execute(email: String, password: String) = authRepository.login(email, password)
}