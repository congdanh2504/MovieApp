package com.training.movieapp.ui.main.utils

import com.training.movieapp.ui.main.model.MainMovie
import com.training.movieapp.ui.main.model.MainSeries
import com.training.movieapp.ui.main.model.Movie
import com.training.movieapp.ui.main.model.Series

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
    private val Series = listOf(
        Series(Images.imageUrl1),
        Series(Images.imageUrl1),
        Series(Images.imageUrl1),
        Series(Images.imageUrl1),
        Series(Images.imageUrl1),
        Series(Images.imageUrl1),
        Series(Images.imageUrl1),
        Series(Images.imageUrl1),
        Series(Images.imageUrl1),
        Series(Images.imageUrl1),
    )
    val collectionsSeries = listOf(
        MainSeries("Trending Now", Series,Trending.TRUE),
        MainSeries("On the Air", Series,Trending.FALSE),
        MainSeries("Airing Today", Series,Trending.FALSE),
        MainSeries("Most Popular", Series,Trending.FALSE),
        MainSeries("Most Anticipated", Series,Trending.FALSE),
    )
}
