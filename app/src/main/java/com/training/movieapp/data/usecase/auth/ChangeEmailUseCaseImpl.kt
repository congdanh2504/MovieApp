package com.training.movieapp.data.usecase.auth

import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.auth.ChangeEmailUseCase
import javax.inject.Inject

class ChangeEmailUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) :
    ChangeEmailUseCase {
    override suspend fun execute(
        email: String,
        currentPassword: String,
        newEmail: String
    ) =
        authRepository.changeEmail(email, currentPassword, newEmail)
}
