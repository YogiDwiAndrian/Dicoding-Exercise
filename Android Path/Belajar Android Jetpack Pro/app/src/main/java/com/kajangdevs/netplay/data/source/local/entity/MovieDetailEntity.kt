package com.kajangdevs.netplay.data.source.local.entity

import com.kajangdevs.netplay.data.source.local.room.entity.MovieBookmarkEntity

data class MovieDetailEntity(
    val movieId: Int,
    val title: String,
    val released: String,
    val runtime: String,
    val rating: Float,
    val poster: String,
    val backdrop: String,
    val voteCount: String,
    val revenue: String,
    val budget: String,
    val synopsis: String,
    val production: String
)

fun MovieDetailEntity.toBookmarkMv(genre: String): MovieBookmarkEntity {
    return MovieBookmarkEntity(
        this.movieId,
        this.title,
        this.released,
        this.runtime,
        this.rating,
        this.poster,
        this.backdrop,
        this.voteCount,
        this.revenue,
        this.budget,
        this.synopsis,
        this.production,
        genre
    )
}