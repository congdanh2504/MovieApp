package com.training.movieapp.domain.usecase

import com.training.movieapp.common.Result
import kotlinx.coroutines.flow.Flow

interface ResetPasswordUseCase {
    suspend fun resetPassword(email: String): Flow<Result<Unit>>
}