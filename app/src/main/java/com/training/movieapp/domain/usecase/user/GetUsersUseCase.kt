package com.training.movieapp.domain.usecase.user

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GetUsersUseCase {
    suspend fun execute(): Flow<Result<List<User>>>
}
