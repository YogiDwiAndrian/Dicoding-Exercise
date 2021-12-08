package com.kajangdevs.netplay.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @field:SerializedName("results")
    val results: List<ResultsItem>
) {

    data class ResultsItem(

        @field:SerializedName("first_air_date")
        val firstAirDate: String,

        @field:SerializedName("overview")
        val overview: String,

        @field:SerializedName("original_language")
        val originalLanguage: String,

        @field:SerializedName("genre_ids")
        val genreIds: List<Int>,

        @field:SerializedName("poster_path")
        val posterPath: String,

        @field:SerializedName("origin_country")
        val originCountry: List<String>,

        @field:SerializedName("backdrop_path")
        val backdropPath: String,

        @field:SerializedName("vote_average")
        val voteAverage: Double,

        @field:SerializedName("popularity")
        val popularity: Double,

        @field:SerializedName("original_name")
        val originalName: String,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("vote_count")
        val voteCount: Int
    )
}