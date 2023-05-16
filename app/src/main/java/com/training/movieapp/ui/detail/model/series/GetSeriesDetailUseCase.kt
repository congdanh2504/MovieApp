package com.training.movieapp.ui.detail.model.series

import com.training.movieapp.common.Result
import com.training.movieapp.ui.detail.model.SeriesDetailView
import kotlinx.coroutines.flow.Flow

interface GetSeriesDetailUseCase {
    suspend fun execute(seriesId: Int): Flow<Result<SeriesDetailView>>
}