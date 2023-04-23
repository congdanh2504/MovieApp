package com.training.movieapp.data.usecase.auth

import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.auth.SignOutUseCase
import javax.inject.Inject

class SignOutUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) :
    SignOutUseCase {
    override suspend fun execute() = authRepository.signOut()
}