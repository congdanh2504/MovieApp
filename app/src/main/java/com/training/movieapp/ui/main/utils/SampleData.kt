package com.training.movieapp.ui.main.utils

import com.training.movieapp.ui.main.model.Companies
import com.training.movieapp.ui.main.model.Network
import com.training.movieapp.ui.main.model.Performer
import com.training.movieapp.ui.main.model.User

object SampleData {
    val User = listOf(
        User(Images.imageUrl1, "Ngoc Khanh"),
        User(Images.imageUrl1, "Khanh Le"),
        User(Images.imageUrl1, "Thuy Hai"),
        User(Images.imageUrl1, "Ngoc Hau"),
        User(Images.imageUrl1, "aasd zzxc"),
        User(Images.imageUrl1, "bzxc aasd")
    )
    val Performer = listOf(
        Performer(Images.imageUrl1, "Le Min Ho"),
        Performer(Images.imageUrl1, "Le Min Ho"),
        Performer(Images.imageUrl1, "Le Min Ho"),
        Performer(Images.imageUrl1, "Le Min Ho"),
        Performer(Images.imageUrl1, "Le Min Ho"),
        Performer(Images.imageUrl1, "Le Min Ho"),
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
        0, 1, 2, 3
    )
}
