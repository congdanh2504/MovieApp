package com.training.movieapp.domain.usecase.auth

import com.training.movieapp.common.Result
import kotlinx.coroutines.flow.Flow

interface RegisterUseCase {
    suspend fun execute(email: String, username: String, password: String): Flow<Result<Unit>>
}
