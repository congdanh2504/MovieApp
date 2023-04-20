package com.training.movieapp.data.usecase

import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.ResetPasswordUseCase
import javax.inject.Inject

class ResetPasswordUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) :
    ResetPasswordUseCase {
    override suspend fun resetPassword(email: String) = authRepository.resetPassword(email)
}