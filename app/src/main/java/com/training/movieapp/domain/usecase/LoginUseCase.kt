package com.training.movieapp.domain.usecase

import com.training.movieapp.common.Result
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    suspend fun login(email: String, password: String): Flow<Result<Unit>>
}