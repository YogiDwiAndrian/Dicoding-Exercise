package com.kajangdevs.netplay.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kajangdevs.netplay.data.source.local.entity.MovieDetailEntity
import com.kajangdevs.netplay.data.source.local.entity.MovieEntity
import com.kajangdevs.netplay.data.source.local.entity.TvShowDetailEntity
import com.kajangdevs.netplay.data.source.local.entity.TvShowEntity
import com.kajangdevs.netplay.data.source.local.room.entity.MovieBookmarkEntity
import com.kajangdevs.netplay.data.source.local.room.entity.TvShowBookmarkEntity

interface DataSource {

    suspend fun fetchMovies(): List<MovieEntity>
    suspend fun fetchTvShow(): List<TvShowEntity>
    suspend fun fetchMoviesSearch(query: String): List<MovieEntity>
    suspend fun fetchTvShowSearch(query: String): List<TvShowEntity>
    suspend fun fetchMovieDetailById(id: Int): MovieDetailEntity
    suspend fun fetchTvShowDetailById(id: Int): TvShowDetailEntity

    fun getBookmarkMovies(): LiveData<PagedList<MovieBookmarkEntity>>
    fun getBookmarkTvShow(): LiveData<PagedList<TvShowBookmarkEntity>>

    suspend fun getBookmarkMovieById(id: Int): MovieBookmarkEntity?
    suspend fun getBookmarkTvShowById(id: Int): TvShowBookmarkEntity?

    suspend fun addBookmarkMovie(movieBookmarkEntity: MovieBookmarkEntity)
    suspend fun addBookmarkTvShow(tvShowBookmarkEntity: TvShowBookmarkEntity)

    suspend fun rmBookmarkMovie(id: Int)
    suspend fun rmBookmarkTvShow(id: Int)
}