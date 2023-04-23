package com.training.movieapp.data.usecase.user

import android.net.Uri
import com.training.movieapp.domain.repository.UserRepository
import com.training.movieapp.domain.usecase.user.UpdateProfileUseCase
import javax.inject.Inject

class UpdateProfileUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    UpdateProfileUseCase {
    override suspend fun execute(
        username: String,
        bio: String,
        imageUri: Uri?
    ) = userRepository.updateProfile(username, bio, imageUri)

}