package com.kajangdevs.netplay.data.source.remote.networking

import com.kajangdevs.netplay.data.source.remote.response.MovieDetailResponse
import com.kajangdevs.netplay.data.source.remote.response.MovieResponse
import com.kajangdevs.netplay.data.source.remote.response.TvShowDetailResponse
import com.kajangdevs.netplay.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int
    ): MovieDetailResponse

    @GET("tv/{id}")
    suspend fun getTvShowDetail(
        @Path("id") id: Int
    ): TvShowDetailResponse

    @GET("movie/now_playing")
    suspend fun getMovies(): MovieResponse

    @GET("tv/popular")
    suspend fun getTvShow(): TvShowResponse

    @GET("search/movie")
    suspend fun getMoviesSearch(
        @Query("query") query: String
    ): MovieResponse

    @GET("search/tv")
    suspend fun getTvShowSearch(
        @Query("query") query: String
    ): TvShowResponse

}