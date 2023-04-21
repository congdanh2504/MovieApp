package com.training.movieapp.data.usecase

import android.net.Uri
import com.training.movieapp.domain.repository.UserRepository
import com.training.movieapp.domain.usecase.UpdateProfileUseCase
import javax.inject.Inject

class UpdateProfileUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    UpdateProfileUseCase {
    override suspend fun updateProfile(
        username: String,
        bio: String,
        imageUri: Uri?
    ) = userRepository.updateProfile(username, bio, imageUri)

}