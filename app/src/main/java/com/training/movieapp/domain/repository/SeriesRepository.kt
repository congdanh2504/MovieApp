package com.training.movieapp.domain.repository

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.TopSeries
import com.training.movieapp.ui.detail.model.SeriesDetailView
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {
    suspend fun getSeries(): Flow<Result<TopSeries>>

    suspend fun getDetailSeries(seriesId: Int): Flow<Result<SeriesDetailView>>
}
