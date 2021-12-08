package com.kajangdevs.netplay

import com.kajangdevs.netplay.data.source.local.entity.*
import com.kajangdevs.netplay.data.source.local.room.entity.MovieBookmarkEntity
import com.kajangdevs.netplay.data.source.local.room.entity.TvShowBookmarkEntity
import com.kajangdevs.netplay.data.source.remote.response.MovieDetailResponse
import com.kajangdevs.netplay.data.source.remote.response.MovieResponse
import com.kajangdevs.netplay.data.source.remote.response.TvShowDetailResponse
import com.kajangdevs.netplay.data.source.remote.response.TvShowResponse

object DataDummy {

    fun testDetailMovie(): List<MovieDetailEntity> {
        return listOf(
            MovieDetailEntity(
                0,
                "Dicoding Test",
                "2020",
                "1 min",
                0.0f,
                "",
                "",
                "",
                "",
                "",
                "",
                ""
            )
        )
    }

    fun testDetailTvSHow(): List<TvShowDetailEntity> {
        return listOf(
            TvShowDetailEntity(
                0,
                "Dicoding Test",
                "2020",
                "1 min",
                0.0f,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
            )
        )
    }

    fun testMovie(): List<MovieEntity> {
        return listOf(
            MovieEntity(
                0,
                "Dicoding Test",
                0.0f,
                "1 min",
                "",
                listOf()
            )
        )
    }

    fun testTvShow(): List<TvShowEntity> {
        return listOf(
            TvShowEntity(
                0,
                "Dicoding Test",
                0.0f,
                "1 min",
                "",
                listOf()
            )
        )
    }

    fun movieResponse(): MovieResponse{
        return MovieResponse(
            results = listOf(
                MovieResponse.ResultsItem(
                   "", "", "", false, "", listOf(), "", "", "",  0.0, 0.0, 1, false, 0
                )
            )
        )
    }

    fun tvShowResponse(): TvShowResponse{
        return TvShowResponse(
            results = listOf(
                TvShowResponse.ResultsItem(
                    "", "", "", listOf(), "", listOf(), "", 0.0, 0.0, "", "", 1, 0
                )
            )
        )
    }

    fun movieDetailResponse(): MovieDetailResponse{
        return MovieDetailResponse("", "", false, "", "", 0, listOf(), 0.0, listOf(), 0, 0,0,"","",0,"",listOf(), listOf(), "",  0.0, "", false, "", "")
    }

    fun tvDetailResponse(): TvShowDetailResponse{
        return TvShowDetailResponse("", 0, listOf(), "", "", listOf(), 0.0, listOf(), 0, 0,0,"","", listOf(), listOf(), listOf(), "",  listOf(), listOf(), listOf(), "", 0.0, "", "", listOf(), false, "", "", ""
        )
    }

    fun movieBookmark(): List<MovieBookmarkEntity>{
        return testDetailMovie().map {
            MovieBookmarkEntity(
                it.movieId,
                it.title,
                it.released,
                it.runtime,
                it.rating,
                it.poster,
                it.backdrop,
                it.voteCount,
                it.revenue,
                it.budget,
                it.synopsis,
                it.production,
                "genre"
            )
        }
    }

    fun tvBookmark(): List<TvShowBookmarkEntity>{
        return testDetailTvSHow().map {
            TvShowBookmarkEntity(
                it.tvShowId,
                it.title,
                it.released,
                it.status,
                it.rating,
                it.poster,
                it.backdrop,
                it.voteCount,
                it.season,
                it.episode,
                it.type,
                it.synopsis,
                it.countries,
                "genre"
            )
        }
    }

}