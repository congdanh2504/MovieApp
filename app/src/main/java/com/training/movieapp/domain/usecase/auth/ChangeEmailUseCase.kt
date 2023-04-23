package com.training.movieapp.domain.usecase.auth

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ChangeEmailUseCase {
    suspend fun execute(
        email: String,
        currentPassword: String,
        newEmail: String
    ): Flow<Result<User>>
}