package com.training.movieapp.data.usecase.auth

import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.auth.ChangePasswordUseCase
import javax.inject.Inject

class ChangePasswordUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) :
    ChangePasswordUseCase {
    override suspend fun execute(
        email: String,
        currentPassword: String,
        newPassword: String
    ) = authRepository.changePassword(email, currentPassword, newPassword)
}