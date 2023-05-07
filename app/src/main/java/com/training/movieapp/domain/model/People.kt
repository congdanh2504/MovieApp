package com.training.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("id") val id: Int,
    @SerializedName("profile_path") val profilePath: String?,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Double,
)