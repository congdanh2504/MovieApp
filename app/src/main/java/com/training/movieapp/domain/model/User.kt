package com.training.movieapp.domain.model

data class User(
    val id: String,
    val username: String,
    val email: String,
    val bio: String = "",
    val imageURL: String?
) {
    constructor() : this("", "" , "", "", "")
}