package com.kajangdevs.netplay.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowDetailResponse(

    @field:SerializedName("original_language")
    val originalLanguage: String,

    @field:SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,

    @field:SerializedName("networks")
    val networks: List<NetworksItem>,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesItem>,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("number_of_seasons")
    val numberOfSeasons: Int,

    @field:SerializedName("vote_count")
    val voteCount: Int,

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("seasons")
    val seasons: List<SeasonsItem>,

    @field:SerializedName("languages")
    val languages: List<String>,

    @field:SerializedName("created_by")
    val createdBy: List<CreatedByItem>,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("origin_country")
    val originCountry: List<String>,

    @field:SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem>,

    @field:SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesItem>,

    @field:SerializedName("original_name")
    val originalName: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("tagline")
    val tagline: String,

    @field:SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,

    @field:SerializedName("in_production")
    val inProduction: Boolean,

    @field:SerializedName("last_air_date")
    val lastAirDate: String,

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

        @field:SerializedName("iso_3166_1")
        val iso31661: String,

        @field:SerializedName("name")
        val name: String
    ) : Parcelable

    @Parcelize
    data class ProductionCompaniesItem(

        @field:SerializedName("logo_path")
        val logoPath: String,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("origin_country")
        val originCountry: String
    ) : Parcelable

    @Parcelize
    data class NetworksItem(

        @field:SerializedName("logo_path")
        val logoPath: String,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("origin_country")
        val originCountry: String
    ) : Parcelable

    @Parcelize
    data class CreatedByItem(

        @field:SerializedName("gender")
        val gender: Int,

        @field:SerializedName("credit_id")
        val creditId: String,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("profile_path")
        val profilePath: String,

        @field:SerializedName("id")
        val id: Int
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


    @Parcelize
    data class SeasonsItem(

        @field:SerializedName("air_date")
        val airDate: String,

        @field:SerializedName("overview")
        val overview: String,

        @field:SerializedName("episode_count")
        val episodeCount: Int,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("season_number")
        val seasonNumber: Int,

        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("poster_path")
        val posterPath: String
    ) : Parcelable
}