package com.training.movieapp.domain.usecase

import com.training.movieapp.common.Result
import kotlinx.coroutines.flow.Flow

interface RegisterUseCase {
    suspend fun register(email: String, username: String, password: String): Flow<Result<Unit>>
}