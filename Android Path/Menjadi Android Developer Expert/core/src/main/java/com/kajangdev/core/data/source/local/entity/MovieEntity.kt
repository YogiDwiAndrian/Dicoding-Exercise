package com.kajangdev.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        val id: Int?,

        @ColumnInfo(name = "title")
        val title: String?,

        @ColumnInfo(name = "overview")
        val overview: String?,

        @ColumnInfo(name = "release_date")
        val released: String?,

        @ColumnInfo(name = "vote_average")
        val rating: Float?,

        @ColumnInfo(name = "vote_count")
        val voteCount: Int?,

        @ColumnInfo(name = "poster_path")
        val poster: String?,

        @ColumnInfo(name = "backdrop_path")
        val backdropPath: String?,

        @ColumnInfo(name = "genre")
        val genre: String?,

        @ColumnInfo(name = "original_language")
        val originalLanguage: String?,

        @ColumnInfo(name = "isTvShow")
        var isTvShow: Boolean? = false,
)