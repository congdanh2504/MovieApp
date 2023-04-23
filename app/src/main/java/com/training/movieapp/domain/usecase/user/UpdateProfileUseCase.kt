package com.training.movieapp.domain.usecase.user

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User

interface UpdateProfileUseCase {
    suspend fun execute(username: String, bio: String, imageUri: Uri?): Flow<Result<User>>
}