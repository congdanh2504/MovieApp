package com.training.movieapp.ui.detail.model.series

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.TopSeries
import kotlinx.coroutines.flow.Flow

interface GetSeriesUseCase {
    suspend fun execute(): Flow<Result<TopSeries>>
}
