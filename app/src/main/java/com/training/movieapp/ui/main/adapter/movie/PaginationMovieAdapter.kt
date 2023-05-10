package com.training.movieapp.ui.main.adapter.movie

import com.training.movieapp.domain.model.Movie

class PaginationMovieAdapter(
    onMovieClick: (Movie) -> Unit,
    private val onLoadMoreMovies: (Int) -> Unit
) :
    MovieAdapter(onMovieClick) {

    private var page = 1
    var totalResult = 0

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (position + 1 == movies.size && movies.size < totalResult) {
            onLoadMoreMovies(++page)
        }
    }

    override fun setMovies(newList: List<Movie>) {
        super.setMovies(newList)
        page = 1
    }

    fun addMovies(newList: List<Movie>) {
        movies.addAll(newList)
        notifyItemInserted(movies.size)
    }
}
