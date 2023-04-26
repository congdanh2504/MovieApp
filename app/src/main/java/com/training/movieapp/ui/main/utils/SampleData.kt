package com.training.movieapp.ui.main.utils

import com.training.movieapp.ui.main.model.*

object SampleData {
    val Movies = listOf(
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
    val User = listOf(
        User(Images.imageUrl1,"Ngoc Khanh"),
        User(Images.imageUrl1,"Khanh Le"),
        User(Images.imageUrl1,"Thuy Hai"),
        User(Images.imageUrl1,"Ngoc Hau"),
        User(Images.imageUrl1,"aasd zzxc"),
        User(Images.imageUrl1,"bzxc aasd")
    )
    val Performer = listOf(
        Performer(Images.imageUrl1,"Le Min Ho"),
        Performer(Images.imageUrl1,"Le Min Ho"),
        Performer(Images.imageUrl1,"Le Min Ho"),
        Performer(Images.imageUrl1,"Le Min Ho"),
        Performer(Images.imageUrl1,"Le Min Ho"),
        Performer(Images.imageUrl1,"Le Min Ho"),
    )
    val Network = listOf(
        Network(Images.imageUrl1),
        Network(Images.imageUrl1),
        Network(Images.imageUrl1),
        Network(Images.imageUrl1),
        Network(Images.imageUrl1),
        Network(Images.imageUrl1),
    )
    val Companies = listOf(
        Companies(Images.imageUrl1),
        Companies(Images.imageUrl1),
        Companies(Images.imageUrl1),
        Companies(Images.imageUrl1),
        Companies(Images.imageUrl1),
        Companies(Images.imageUrl1),
        Companies(Images.imageUrl1),
    )
    val listView = listOf<Int>(
        0,1,2,3
    )
}
