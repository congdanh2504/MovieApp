package com.training.movieapp.domain.usecase

import com.training.movieapp.domain.model.User

interface SaveUserUseCase {
    suspend fun saveUser(user: User)
}