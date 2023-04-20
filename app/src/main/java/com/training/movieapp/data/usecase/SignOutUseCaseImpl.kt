package com.training.movieapp.data.usecase

import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.SignOutUseCase
import javax.inject.Inject

class SignOutUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) :
    SignOutUseCase {
    override suspend fun signOut() = authRepository.signOut()
}