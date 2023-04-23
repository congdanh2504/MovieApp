package com.training.movieapp.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import com.training.movieapp.common.Result

interface ChangePasswordUseCase {
    suspend fun execute(
        email: String,
        currentPassword: String,
        newPassword: String
    ): Flow<Result<Unit>>
}