package com.kajangdevs.netplay.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailResponse(

    @field:SerializedName("original_language")
    val originalLanguage: String,

    @field:SerializedName("imdb_id")
    val imdbId: String,

    @field:SerializedName("video")
    val video: Boolean,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("revenue")
    val revenue: Long,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesItem>,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("vote_count")
    val voteCount: Int,

    @field:SerializedName("budget")
    val budget: Long,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("original_title")
    val originalTitle: String,

    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem>,

    @field:SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesItem>,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("tagline")
    val tagline: String,

    @field:SerializedName("adult")
    val adult: Boolean,

    @field:SerializedName("homepage")
    val homepage: String,

    @field:SerializedName("status")
    val status: String
) : Parcelable {

    @Parcelize
    data class GenresItem(

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("id")
        val id: Int
    ) : Parcelable

    @Parcelize
    data class ProductionCountriesItem(

        @field:SerializedName("name")
        val name: String

    ) : Parcelable

    @Parcelize
    data class ProductionCompaniesItem(

        @field:SerializedName("logo_path")
        val logoPath: String? = null,


        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("origin_country")
        val originCountry: String
    ) : Parcelable

    @Parcelize
    data class SpokenLanguagesItem(

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("iso_639_1")
        val iso6391: String,

        @field:SerializedName("english_name")
        val englishName: String
    ) : Parcelable
}
