package com.training.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("id") val id: Int,
    @SerializedName("profile_path") val profilePath: String?,
    @SerializedName("character") val character: String,
    @SerializedName("name") val name: String
)