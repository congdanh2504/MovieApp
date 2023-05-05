package com.training.movieapp.data.usecase.datastore

import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.repository.DataStoreRepository
import com.training.movieapp.domain.usecase.datastore.SaveUserUseCase
import javax.inject.Inject

class SaveUserUseCaseImpl @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    SaveUserUseCase {
    override suspend fun execute(user: User) = dataStoreRepository.saveUser(user)
}
