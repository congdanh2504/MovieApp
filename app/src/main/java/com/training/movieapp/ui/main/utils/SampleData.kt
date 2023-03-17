package com.training.movieapp.ui.main.utils

import com.training.movieapp.ui.main.model.MainMovie
import com.training.movieapp.ui.main.model.Movie

object SampleData {
    private val Movies = listOf(
        Movie(Images.imageUrl1),
        Movie(Images.imageUrl1),
        Movie(Images.imageUrl1),
        Movie(Images.imageUrl1),
        Movie(Images.imageUrl1),
        Movie(Images.imageUrl1),
        Movie(Images.imageUrl1),
        Movie(Images.imageUrl1),
        Movie(Images.imageUrl1),
    )
    val collections = listOf(
        MainMovie("Trending Now", Movies,Trending.TRUE),
        MainMovie("Popular on Letterboxd", Movies,Trending.FALSE),
        MainMovie("In Theaters", Movies.reversed(),Trending.FALSE),
        MainMovie("Coming Soon", Movies.shuffled(),Trending.FALSE),
        MainMovie("Most Popular", Movies,Trending.FALSE),
        MainMovie("Most Anticipated", Movies,Trending.FALSE),
    )
}
