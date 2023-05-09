package com.training.movieapp.domain.usecase.series

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.Series
import kotlinx.coroutines.flow.Flow

interface GetSeriesUseCase {
    suspend fun execute(): Flow<Result<Series>>
}
