package com.training.movieapp.domain.usecase.user

import kotlinx.coroutines.flow.Flow

interface CheckUserLoggedInUseCase {
    suspend fun execute(): Flow<Boolean>
}
