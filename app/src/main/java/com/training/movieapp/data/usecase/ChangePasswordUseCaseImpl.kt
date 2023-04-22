package com.training.movieapp.data.usecase

import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.ChangePasswordUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangePasswordUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) :
    ChangePasswordUseCase {
    override suspend fun changePassword(
        email: String,
        currentPassword: String,
        newPassword: String
    ) = authRepository.changePassword(email, currentPassword, newPassword)
}