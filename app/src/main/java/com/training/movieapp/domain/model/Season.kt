package com.training.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class Season(
    @SerializedName("air_date") val air_date: String,
    @SerializedName("episode_count") val episode_count: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster_path: Any,
    @SerializedName("season_number") val season_number: Int
)
