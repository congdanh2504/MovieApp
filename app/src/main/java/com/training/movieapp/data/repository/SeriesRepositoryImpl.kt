package com.training.movieapp.data.repository

import com.training.movieapp.common.Result
import com.training.movieapp.data.remote.SeriesApi
import com.training.movieapp.domain.model.TopSeries
import com.training.movieapp.domain.repository.SeriesRepository
import com.training.movieapp.ui.detail.model.SeriesDetailView
import kotlinx.coroutines.flow.Flow
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
            emit(
                Result.Success(
                    TopSeries(
                        seriesTrending,
                        seriesAiringToday,
                        seriesOnTheAir,
                        seriesPopular
                    )
                )
            )
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun getDetailSeries(seriesId: Int): Flow<Result<SeriesDetailView>> = flow {
        try {
            val seriesDetail = seriesApi.getSeriesDetail(seriesId)
            val seriesCredits = seriesApi.getSeriesCredits(seriesId)
            val similarSeries = seriesApi.getSimilarSeries(seriesId)
            emit(
                Result.Success(
                    SeriesDetailView(
                        seriesDetail,
                        seriesCredits,
                        similarSeries.results
                    )
                )
            )
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}
