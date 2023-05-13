package com.training.movieapp.domain.usecase.movie

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.VideoResponse
import kotlinx.coroutines.flow.Flow

interface GetVideosUseCase {
    suspend fun execute(movieId: Int): Flow<Result<VideoResponse>>
}