package com.training.movieapp.data.usecase

import com.training.movieapp.domain.repository.DataStoreRepository
import com.training.movieapp.domain.usecase.ReadUserUseCase
import javax.inject.Inject

class ReadUserUseCaseImpl @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ReadUserUseCase {
    override suspend fun readUser() = dataStoreRepository.readUser()
}