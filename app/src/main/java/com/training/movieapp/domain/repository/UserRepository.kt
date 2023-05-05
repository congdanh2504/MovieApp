package com.training.movieapp.domain.repository

import android.net.Uri
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun updateProfile(username: String, bio: String, imageUri: Uri?): Flow<Result<User>>
    suspend fun checkUserIsLogged(): Flow<Boolean>
}
