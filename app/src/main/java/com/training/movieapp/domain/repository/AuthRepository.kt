package com.training.movieapp.domain.repository

import com.training.movieapp.common.Result

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun register(email: String, username: String, password: String): Result<Unit>
    suspend fun signOut(): Result<Unit>
    suspend fun resetPassword(email: String): Result<Unit>
}