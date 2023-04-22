package com.training.movieapp.data.usecase

import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.repository.DataStoreRepository
import com.training.movieapp.domain.usecase.SaveUserUseCase
import javax.inject.Inject

class SaveUserUseCaseImpl @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    SaveUserUseCase {
    override suspend fun saveUser(user: User) = dataStoreRepository.saveUser(user)
}