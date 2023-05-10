package com.training.movieapp.data.usecase.series

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.Series
import com.training.movieapp.domain.repository.SeriesRepository
import com.training.movieapp.domain.usecase.series.GetSeriesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSeriesUseCaseImpl @Inject constructor(private val seriesRepository: SeriesRepository) :
    GetSeriesUseCase {
    override suspend fun execute(): Flow<Result<Series>> {
        return seriesRepository.getSeries()
    }
}
