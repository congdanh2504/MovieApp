package com.training.movieapp.ui.main.model

import com.training.movieapp.domain.model.Movie
import com.training.movieapp.ui.main.utils.Trending

data class MainMovie(
    val title: String,
    val movieModels: List<Movie>,
    val trending: Trending
)
