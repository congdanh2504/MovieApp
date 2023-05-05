package com.training.movieapp.domain.usecase.auth

import com.training.movieapp.common.Result
import kotlinx.coroutines.flow.Flow

interface SignOutUseCase {
    suspend fun execute(): Flow<Result<Unit>>
}
