package com.training.movieapp.domain.usecase.auth

import com.training.movieapp.common.Result
import kotlinx.coroutines.flow.Flow

interface ResetPasswordUseCase {
    suspend fun execute(email: String): Flow<Result<Unit>>
}