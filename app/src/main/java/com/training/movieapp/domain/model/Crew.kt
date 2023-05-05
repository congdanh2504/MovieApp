package com.training.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class Crew(
    @SerializedName("id") val id: Int,
    @SerializedName("profile_path") val profilePath: String?,
    @SerializedName("job") val job: String,
    @SerializedName("name") val name: String
)
