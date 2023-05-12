package com.training.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class CompanyDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("headquarters") val headquarters: String,
    @SerializedName("description") val description: String,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("logo_path") val logoPath: String,
    @SerializedName("name") val name: String,
    @SerializedName("origin_country") val originCountry: String
)
