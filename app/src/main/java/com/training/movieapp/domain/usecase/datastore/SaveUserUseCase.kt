package com.training.movieapp.domain.usecase.datastore

import com.training.movieapp.domain.model.User

interface SaveUserUseCase {
    suspend fun execute(user: User)
}