package com.training.movieapp.ui.main.utils

import com.training.movieapp.domain.model.People
import com.training.movieapp.domain.model.User
import com.training.movieapp.ui.main.model.Companies
import com.training.movieapp.ui.main.model.Network

object SampleData {
    var User = listOf<User>()
    var Performer = listOf<People>()
    val Network = listOf(
        Network("https://image.tmdb.org/t/p/w200/1dtumlarEVXeYX1BrUFS9qLoG2Z.png"),
        Network("https://image.tmdb.org/t/p/w200/dtnGkrdsbMsTRDBIxGbciiqWgwO.png"),
        Network("https://image.tmdb.org/t/p/w200/wwemzKWzjKYJFfCeiB57q3r4Bcm.png"),
        Network("https://image.tmdb.org/t/p/w200//apAZlt5T2uvebXaEN4f5Zw4C1pK.png"),
        Network("https://image.tmdb.org/t/p/w200/nTNJrWvGt1DT9iwERf81hSPrrws.png"),
        Network("https://image.tmdb.org/t/p/w200/aq8hpQPfhUSEwohm27RAhYLnKZn.png"),
        Network("https://image.tmdb.org/t/p/w200//h04Po2f2Uqq19xYHB4mbkjC7njG.png"),
        Network("https://image.tmdb.org/t/p/w200/ar0fBQkxzbBYe4S8zEGnrfZNBnm.png"),
    )
    val Companies = listOf(
        Companies("https://image.tmdb.org/t/p/w200/o86DbpburjxrqAzEDhXZcyE8pDb.png"),
        Companies("https://image.tmdb.org/t/p/w200/lcpIJu30UucUTE5SCKYD1qFdDHr.png"),
        Companies("https://image.tmdb.org/t/p/w200/8lvHyhjr8oUKOOy2dKXoALWKdp0.png"),
        Companies("https://image.tmdb.org/t/p/w200/fOG2oY4m1YuYTQh4bMqqZkmgOAI.png"),
        Companies("https://image.tmdb.org/t/p/w200/e4dQAqZD374H5EuM0W1ljEBWTKy.png"),
        Companies("https://image.tmdb.org/t/p/w200/IuAlhI9eVC9Z8UQWOIDdWRKSEJ.png"),
        Companies("https://image.tmdb.org/t/p/w200/iiHe9y7Qbbts4agxQGQ81nLzrB9.png"),
        Companies("https://image.tmdb.org/t/p/w200/hUzeosd33nzE5MCNsZxCGEKTXaQ.png"),
        )
    val listView = listOf<Int>(
        0, 1, 2, 3
    )
}
