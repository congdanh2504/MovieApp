package com.training.movieapp.data.usecase.auth

import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.auth.ResetPasswordUseCase
import javax.inject.Inject

class ResetPasswordUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) :
    ResetPasswordUseCase {
    override suspend fun execute(email: String) = authRepository.resetPassword(email)
}
