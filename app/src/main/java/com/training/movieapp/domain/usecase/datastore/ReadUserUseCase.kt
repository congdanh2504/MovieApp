package com.training.movieapp.domain.usecase.datastore

import com.training.movieapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ReadUserUseCase {
    suspend fun execute(): Flow<User>
}