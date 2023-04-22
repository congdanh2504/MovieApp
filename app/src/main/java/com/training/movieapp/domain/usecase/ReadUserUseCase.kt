package com.training.movieapp.domain.usecase

import com.training.movieapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ReadUserUseCase {
    suspend fun readUser(): Flow<User>
}