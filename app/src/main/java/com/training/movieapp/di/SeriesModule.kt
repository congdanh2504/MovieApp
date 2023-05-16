package com.training.movieapp.di

import com.training.movieapp.data.repository.SeriesRepositoryImpl
import com.training.movieapp.data.usecase.series.GetSeriesDetailUseCaseImpl
import com.training.movieapp.data.usecase.series.GetSeriesUseCaseImpl
import com.training.movieapp.domain.repository.SeriesRepository
import com.training.movieapp.ui.detail.model.series.GetSeriesDetailUseCase
import com.training.movieapp.ui.detail.model.series.GetSeriesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SeriesModule {

    @Binds
    abstract fun bindGetSeries(impl: SeriesRepositoryImpl): SeriesRepository

    @Binds
    abstract fun bindGetSeriesUseCase(impl: GetSeriesUseCaseImpl): GetSeriesUseCase

    @Binds
    abstract fun bindGetSeriesDetailUseCase(impl: GetSeriesDetailUseCaseImpl): GetSeriesDetailUseCase
}
