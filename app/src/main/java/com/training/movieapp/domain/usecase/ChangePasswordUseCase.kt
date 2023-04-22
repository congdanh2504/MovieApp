package com.training.movieapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.training.movieapp.common.Result

interface ChangePasswordUseCase {
    suspend fun changePassword(
        email: String,
        currentPassword: String,
        newPassword: String
    ): Flow<Result<Unit>>
}