package com.training.movieapp.di

import com.training.movieapp.BuildConfig
import com.training.movieapp.common.Constant
import com.training.movieapp.data.remote.TheMovieDbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TheMovieDbApiModule {

    @Singleton
    @Provides
    fun provideTheMovieDbApi(): TheMovieDbApi = Retrofit.Builder()
        .baseUrl(Constant.TMDB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().addInterceptor { chain ->
            var request = chain.request()
            val url = request.url.newBuilder().addQueryParameter(
                "api_key",
                BuildConfig.API_KEY
            ).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }.build())
        .build().create(TheMovieDbApi::class.java)
}