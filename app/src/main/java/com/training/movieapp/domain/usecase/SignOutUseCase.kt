package com.training.movieapp.domain.usecase

import com.training.movieapp.common.Result
import kotlinx.coroutines.flow.Flow

interface SignOutUseCase {
    suspend fun signOut(): Flow<Result<Unit>>
}