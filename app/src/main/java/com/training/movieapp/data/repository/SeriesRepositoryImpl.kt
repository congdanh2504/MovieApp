package com.training.movieapp.data.repository

import com.training.movieapp.common.Result
import com.training.movieapp.data.remote.TheSeriesDbApi
import com.training.movieapp.domain.model.Series
import com.training.movieapp.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(private val theSeriesDbApi: TheSeriesDbApi) :
    SeriesRepository {
    override suspend fun getSeries() = flow {
        try {
            val seriesTrending = theSeriesDbApi.getSeriesTrending()
            val seriesPopular = theSeriesDbApi.getSeriesPopular()
            val seriesAiringToday = theSeriesDbApi.getSeriesAiringToday()
            val seriesOnTheAir = theSeriesDbApi.getSeriesOnTheAir()
            emit(Result.Success(Series(seriesTrending, seriesAiringToday, seriesOnTheAir, seriesPopular)))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}
