package com.training.movieapp.data.usecase.datastore

import com.training.movieapp.domain.repository.DataStoreRepository
import com.training.movieapp.domain.usecase.datastore.ReadUserUseCase
import javax.inject.Inject

class ReadUserUseCaseImpl @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ReadUserUseCase {
    override suspend fun execute() = dataStoreRepository.readUser()
}