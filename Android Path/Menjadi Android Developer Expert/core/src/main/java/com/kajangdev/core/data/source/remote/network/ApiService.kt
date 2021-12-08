package com.kajangdev.core.data.source.remote.network

import com.kajangdev.core.data.source.remote.response.ListMovieResponse
import com.kajangdev.core.data.source.remote.response.ListTvResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getMovies(
            @Query("api_key") apiKey: String
    ): ListMovieResponse

    @GET("tv/popular")
    suspend fun getTvShow(
            @Query("api_key") apiKey: String
    ): ListTvResponse

    @GET("search/movie")
    suspend fun getMovieSearch(
            @Query("api_key") apiKey: String,
            @Query("query") query: String
    ): ListMovieResponse

    @GET("search/tv")
    suspend fun getTvShowSearch(
            @Query("api_key") apiKey: String,
            @Query("query") query: String
    ): ListTvResponse
}