package com.training.movieapp.di

import com.training.movieapp.data.repository.MovieRepositoryImpl
import com.training.movieapp.data.usecase.movie.GetDetailMovieUseCaseImpl
import com.training.movieapp.data.usecase.movie.GetTrendingMovieUseCaseImpl
import com.training.movieapp.domain.repository.MovieRepository
import com.training.movieapp.domain.usecase.movie.GetDetailMovieUseCase
import com.training.movieapp.domain.usecase.movie.GetTrendingMovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieModule {

    @Binds
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindGetDetailMovieUseCase(impl: GetDetailMovieUseCaseImpl): GetDetailMovieUseCase

    @Binds
    abstract fun bindGetTrendingMovieUseCase(impl: GetTrendingMovieUseCaseImpl): GetTrendingMovieUseCase
}