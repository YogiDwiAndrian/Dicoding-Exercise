package com.kajangdevs.netplay.data.source.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviebookmark")
data class MovieBookmarkEntity(

    @ColumnInfo(name = "id")
    val movieId: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "release_date")
    val released: String,

    @ColumnInfo(name = "runtime")
    val runtime: String,

    @ColumnInfo(name = "vote_average")
    val rating: Float,

    @ColumnInfo(name = "poster_path")
    val poster: String,

    @ColumnInfo(name = "backdrop_path")
    val backdrop: String,

    @ColumnInfo(name = "vote_count")
    val voteCount: String,

    @ColumnInfo(name = "revenue")
    val revenue: String,

    @ColumnInfo(name = "budget")
    val budget: String,

    @ColumnInfo(name = "overview")
    val synopsis: String,

    @ColumnInfo(name = "production_countries")
    val production: String,

    @ColumnInfo(name = "genre")
    val genre: String
) {
    @PrimaryKey(autoGenerate = true)
    var movieBmId: Int = 0
}