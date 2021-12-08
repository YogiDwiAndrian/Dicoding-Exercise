package com.kajangdevs.netplay.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource
import com.kajangdevs.netplay.data.source.local.entity.MovieDetailEntity
import com.kajangdevs.netplay.data.source.local.entity.MovieEntity
import com.kajangdevs.netplay.data.source.local.entity.TvShowDetailEntity
import com.kajangdevs.netplay.data.source.local.entity.TvShowEntity
import com.kajangdevs.netplay.data.source.local.room.BookmarkDao
import com.kajangdevs.netplay.data.source.local.room.entity.MovieBookmarkEntity
import com.kajangdevs.netplay.data.source.local.room.entity.TvShowBookmarkEntity
import com.kajangdevs.netplay.data.source.remote.networking.ApiService
import com.kajangdevs.netplay.utils.*

class Repository(
    private val apiService: ApiService,
    private val bookmarkDao: BookmarkDao,
    private val idlingResource: IdlingResource
) : DataSource {

    override suspend fun fetchMovies(): List<MovieEntity> {
        (idlingResource as CountingIdlingResource).increment()
        try {
            val movieResult = apiService
                .getMovies()
                .results.map {
                    MovieEntity(
                        it.id,
                        it.title,
                        it.voteAverage.toFloat(),
                        it.releaseDate.toYearDateFormat(),
                        it.posterPath.asPosterUrl(),
                        it.genreIds
                    )
                }
            idlingResource.decrement()
            return movieResult
        } catch (e: Throwable) {
            idlingResource.decrement()
            return listOf()
        }
    }

    override suspend fun fetchTvShow(): List<TvShowEntity> {
        (idlingResource as CountingIdlingResource).increment()
        try {
            val tvResult = apiService
                .getTvShow()
                .results.map {
                    TvShowEntity(
                        it.id,
                        it.name,
                        it.voteAverage.toFloat(),
                        it.firstAirDate.toYearDateFormat(),
                        it.posterPath.asPosterUrl(),
                        it.genreIds
                    )
                }
            idlingResource.decrement()
            return tvResult
        } catch (e: Throwable) {
            idlingResource.decrement()
            return listOf()
        }
    }

    override suspend fun fetchMoviesSearch(query: String): List<MovieEntity> {
        (idlingResource as CountingIdlingResource).increment()
        try {
            val movieResult = apiService
                .getMoviesSearch(query)
                .results.map {
                    MovieEntity(
                        it.id,
                        it.title,
                        it.voteAverage.toFloat(),
                        it.releaseDate.toYearDateFormat(),
                        it.posterPath.asPosterUrl(),
                        it.genreIds
                    )
                }
            idlingResource.decrement()
            return movieResult
        } catch (e: Throwable) {
            idlingResource.decrement()
            return listOf()
        }
    }

    override suspend fun fetchTvShowSearch(query: String): List<TvShowEntity> {
        (idlingResource as CountingIdlingResource).increment()
        try {
            val tvResult = apiService
                .getTvShowSearch(query)
                .results.map {
                    TvShowEntity(
                        it.id,
                        it.name,
                        it.voteAverage.toFloat(),
                        it.firstAirDate.toYearDateFormat(),
                        it.posterPath.asPosterUrl(),
                        it.genreIds
                    )
                }
            idlingResource.decrement()
            return tvResult
        } catch (e: Throwable) {
            idlingResource.decrement()
            return listOf()
        }
    }


    override suspend fun fetchMovieDetailById(id: Int): MovieDetailEntity {
        (idlingResource as CountingIdlingResource).increment()
        try {
            val movieDetailResult = apiService.getMovieDetail(id)
            idlingResource.decrement()
            return MovieDetailEntity(
                movieDetailResult.id,
                movieDetailResult.title,
                movieDetailResult.releaseDate.toYearDateFormat(),
                movieDetailResult.runtime.toString(),
                movieDetailResult.voteAverage.toFloat(),
                movieDetailResult.posterPath.asPosterUrl(),
                movieDetailResult.backdropPath.asBackdropUrl(),
                movieDetailResult.voteCount.toString(),
                movieDetailResult.revenue.withSuffix(),
                movieDetailResult.budget.withSuffix(),
                movieDetailResult.overview,
                movieDetailResult.productionCountries.map { it.name }.toString().rmBracket()
            )
        } catch (e: Throwable) {
            println("ERROR: ${e.localizedMessage}")
            idlingResource.decrement()
            return MovieDetailEntity(0, "", "", "", 0.0f, "", "", "", "", "", "", "")
        }
    }

    override suspend fun fetchTvShowDetailById(id: Int): TvShowDetailEntity {
        (idlingResource as CountingIdlingResource).increment()
        try {
            val tvDetailResult = apiService.getTvShowDetail(id)
            idlingResource.decrement()
            return TvShowDetailEntity(
                tvDetailResult.id,
                tvDetailResult.name,
                tvDetailResult.firstAirDate.toYearDateFormat(),
                tvDetailResult.status,
                tvDetailResult.voteAverage.toFloat(),
                tvDetailResult.posterPath.asPosterUrl(),
                tvDetailResult.backdropPath.asBackdropUrl(),
                tvDetailResult.voteCount.toString(),
                tvDetailResult.numberOfEpisodes.toString(),
                tvDetailResult.numberOfSeasons.toString(),
                tvDetailResult.type,
                tvDetailResult.overview,
                tvDetailResult.productionCountries.map { it.name }.toString().rmBracket()
            )
        } catch (e: Throwable) {
            println("ERROR: ${e.localizedMessage}")
            idlingResource.decrement()
            return TvShowDetailEntity(0, "", "", "", 0.0f, "", "", "", "", "", "", "", "")
        }
    }

    override fun getBookmarkMovies(): LiveData<PagedList<MovieBookmarkEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(bookmarkDao.getBookmarkMovies(), config).build()
    }


    override fun getBookmarkTvShow(): LiveData<PagedList<TvShowBookmarkEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(bookmarkDao.getBookmarkTvShow(), config).build()
    }

    override suspend fun getBookmarkMovieById(id: Int): MovieBookmarkEntity? =
        bookmarkDao.getBookmarkMovieById(id)

    override suspend fun getBookmarkTvShowById(id: Int): TvShowBookmarkEntity? =
        bookmarkDao.getBookmarkTvShowById(id)

    override suspend fun addBookmarkMovie(movieBookmarkEntity: MovieBookmarkEntity) =
        bookmarkDao.insertMv(movieBookmarkEntity)

    override suspend fun addBookmarkTvShow(tvShowBookmarkEntity: TvShowBookmarkEntity) =
        bookmarkDao.insertTv(tvShowBookmarkEntity)

    override suspend fun rmBookmarkMovie(id: Int) =
        bookmarkDao.deleteMv(id)

    override suspend fun rmBookmarkTvShow(id: Int) =
        bookmarkDao.deleteTv(id)

}