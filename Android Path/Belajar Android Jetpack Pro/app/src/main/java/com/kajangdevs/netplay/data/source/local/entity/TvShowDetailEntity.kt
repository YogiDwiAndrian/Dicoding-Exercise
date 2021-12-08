package com.kajangdevs.netplay.data.source.local.entity

import com.kajangdevs.netplay.data.source.local.room.entity.TvShowBookmarkEntity

data class TvShowDetailEntity(
    val tvShowId: Int,
    val title: String,
    val released: String,
    val status: String,
    val rating: Float,
    val poster: String,
    val backdrop: String,
    val voteCount: String,
    val season: String,
    val episode: String,
    val type: String,
    val synopsis: String,
    val countries: String
)

fun TvShowDetailEntity.toBookmarkTv(genre: String): TvShowBookmarkEntity {
    return TvShowBookmarkEntity(
        this.tvShowId,
        this.title,
        this.released,
        this.status,
        this.rating,
        this.poster,
        this.backdrop,
        this.voteCount,
        this.season,
        this.episode,
        this.type,
        this.synopsis,
        this.countries,
        genre
    )
}