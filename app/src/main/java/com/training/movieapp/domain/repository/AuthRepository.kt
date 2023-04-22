package com.training.movieapp.domain.repository

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<Result<User>>
    suspend fun register(email: String, username: String, password: String): Flow<Result<Unit>>
    suspend fun signOut(): Flow<Result<Unit>>
    suspend fun resetPassword(email: String): Flow<Result<Unit>>
    suspend fun changePassword(
        email: String,
        currentPassword: String,
        newPassword: String
    ): Flow<Result<Unit>>
}