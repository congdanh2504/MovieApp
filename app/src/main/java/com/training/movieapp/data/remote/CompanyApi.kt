package com.training.movieapp.data.remote

import com.training.movieapp.domain.model.CompanyDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface CompanyApi {

    @GET("company/{company_id}")
    suspend fun getCompanyDetail(@Path("company_id") companyId: Int): CompanyDetail
}