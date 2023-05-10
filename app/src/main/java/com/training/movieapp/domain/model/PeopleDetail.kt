package com.training.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class PeopleDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("birthday") val birthday: String?,
    @SerializedName("name") val name: String,
    @SerializedName("biography") val biography: String,
    @SerializedName("place_of_birth") val placeOfBirth: String?,
    @SerializedName("known_for_department") val knownForDepartment: String,
    @SerializedName("profile_path") val profilePath: String?,
)
