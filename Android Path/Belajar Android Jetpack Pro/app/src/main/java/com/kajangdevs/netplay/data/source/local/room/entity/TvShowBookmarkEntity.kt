package com.kajangdevs.netplay.data.source.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowbookmark")
data class TvShowBookmarkEntity(

    @ColumnInfo(name = "id")
    val tvShowId: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "first_air_date")
    val released: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "vote_average")
    val rating: Float,

    @ColumnInfo(name = "poster_path")
    val poster: String,

    @ColumnInfo(name = "backdrop_path")
    val backdrop: String,

    @ColumnInfo(name = "vote_count")
    val voteCount: String,

    @ColumnInfo(name = "number_of_seasons")
    val season: String,

    @ColumnInfo(name = "number_of_episodes")
    val episode: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "overview")
    val synopsis: String,

    @ColumnInfo(name = "production_countries")
    val countries: String,

    @ColumnInfo(name = "genre")
    val genre: String
) {
    @PrimaryKey(autoGenerate = true)
    var tvShowBmId: Int = 0

}