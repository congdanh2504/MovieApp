package com.training.movieapp.domain.model

data class Cast(
    val id: Int,
    val profilePath: String?,
    val character: String,
    val name: String
)