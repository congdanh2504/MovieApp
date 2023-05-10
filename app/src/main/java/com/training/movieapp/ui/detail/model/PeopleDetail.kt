package com.training.movieapp.ui.detail.model

import com.training.movieapp.domain.model.MovieCredits

data class PeopleDetail(
    val id: Int,
    val birthday: String?,
    val name: String,
    val biography: String,
    val placeOfBirth: String?,
    val knownForDepartment: String,
    val profilePath: String?,
    val movieCredits: MovieCredits
)