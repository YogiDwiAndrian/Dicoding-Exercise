package com.kajangdev.core.utils

import com.kajangdev.core.data.source.local.entity.MovieEntity
import com.kajangdev.core.data.source.remote.response.MovieResponse
import com.kajangdev.core.data.source.remote.response.TvResponse
import com.kajangdev.core.domain.model.Movie
import com.kajangdev.core.domain.model.Slides
import com.kajangdev.core.utils.Constant.getGenreNameMv
import com.kajangdev.core.utils.Constant.getGenreNameTv


object DataMapper {
    fun movieResponsesToDomain(input: List<MovieResponse>): List<Movie> {
        val movieList = ArrayList<Movie>()
        input.map {
            val movie = Movie(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    rating = it.voteAverage!!.toFloat(),
                    voteCount = it.voteCount.toString(),
                    released = it.releaseDate?.let { date -> toYearDateFormat(date) },
                    poster = it.posterPath?.let { url -> asPosterUrl(url) },
                    backdrop = it.backdropPath?.let { url -> asBackdropUrl(url) },
                    genre = getGenreNameMv(it.genreIds),
                    language = it.originalLanguage,
                    isTvShow = false,
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun tvResponsesToDomain(input: List<TvResponse>): List<Movie> {
        val tvList = ArrayList<Movie>()
        input.map {
            val tv = Movie(
                    id = it.id,
                    title = it.name,
                    overview = it.overview,
                    rating = it.voteAverage!!.toFloat(),
                    voteCount = it.voteCount.toString(),
                    released = it.firstAirDate?.let { date -> toYearDateFormat(date) },
                    poster = it.posterPath?.let { url -> asPosterUrl(url) },
                    backdrop = it.backdropPath?.let { url -> asPosterUrl(url) },
                    genre = getGenreNameTv(it.genreIds),
                    language = it.originalLanguage,
                    isTvShow = true,
            )
            tvList.add(tv)
        }
        return tvList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
            input.map {
                Movie(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        rating = it.rating,
                        voteCount = it.voteCount.toString(),
                        released = it.released,
                        poster = it.poster,
                        backdrop = it.backdropPath,
                        genre = it.genre,
                        language = it.originalLanguage,
                        isTvShow = it.isTvShow,
                )
            }

    fun mapEntityToDomain(input: MovieEntity?): Movie =
            Movie(
                    id = input?.id,
                    title = input?.title,
                    overview = input?.overview,
                    rating = input?.rating,
                    voteCount = input?.voteCount.toString(),
                    released = input?.released,
                    poster = input?.poster,
                    backdrop = input?.backdropPath,
                    genre = input?.genre,
                    language = input?.originalLanguage,
                    isTvShow = input?.isTvShow,
            )

    fun mapDomainToEntity(input: Movie) = MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            rating = input.rating,
            voteCount = input.voteCount!!.toInt(),
            released = input.released,
            poster = input.poster,
            backdropPath = input.backdrop,
            genre = input.genre,
            originalLanguage = input.language,
            isTvShow = input.isTvShow,
    )

    fun responseToSlides(input: List<MovieResponse>): List<Slides> {
        val slideList = ArrayList<Slides>()
        for (data in 1..5){
            val movie = Slides(
                    input[data].backdropPath?.let { url -> asBackdropUrl(url) }
            )
            slideList.add(movie)
        }
        return slideList
    }
}