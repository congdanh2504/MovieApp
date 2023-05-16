package com.training.movieapp.data.usecase.series

import com.training.movieapp.domain.repository.SeriesRepository
import com.training.movieapp.ui.detail.model.series.GetSeriesDetailUseCase
import javax.inject.Inject

class GetSeriesDetailUseCaseImpl @Inject constructor(private val seriesRepository: SeriesRepository) :
    GetSeriesDetailUseCase {
    override suspend fun execute(seriesId: Int) = seriesRepository.getDetailSeries(seriesId)
}