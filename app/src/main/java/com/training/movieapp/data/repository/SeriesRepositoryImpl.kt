package com.training.movieapp.data.repository

import com.training.movieapp.common.Result
import com.training.movieapp.data.remote.SeriesApi
import com.training.movieapp.domain.model.Series
import com.training.movieapp.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(private val seriesApi: SeriesApi) :
    SeriesRepository {
    override suspend fun getSeries() = flow {
        try {
            val seriesTrending = seriesApi.getSeriesTrending()
            val seriesPopular = seriesApi.getSeriesPopular()
            val seriesAiringToday = seriesApi.getSeriesAiringToday()
            val seriesOnTheAir = seriesApi.getSeriesOnTheAir()
            emit(Result.Success(Series(seriesTrending, seriesAiringToday, seriesOnTheAir, seriesPopular)))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}
