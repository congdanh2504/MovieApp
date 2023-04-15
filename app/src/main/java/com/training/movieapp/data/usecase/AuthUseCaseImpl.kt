package com.training.movieapp.data.usecase

import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.AuthUseCase
import javax.inject.Inject
import com.training.movieapp.domain.model.Result

class AuthUseCaseImpl @Inject constructor(private val authRepository: AuthRepository): AuthUseCase {
    override suspend fun login(email: String, password: String) = authRepository.login(email, password)

    override suspend fun register(email: String, username: String, password: String) = authRepository.register(email, username, password)

    override suspend fun signOut() = authRepository.signOut()

    override suspend fun resetPassword(email: String): Result<Unit> = authRepository.resetPassword(email)
}