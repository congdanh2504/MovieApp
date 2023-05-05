package com.training.movieapp.domain.usecase.auth

import com.training.movieapp.common.Result
import kotlinx.coroutines.flow.Flow

interface ChangePasswordUseCase {
    suspend fun execute(
        email: String,
        currentPassword: String,
        newPassword: String
    ): Flow<Result<Unit>>
}
