package com.kajangdev.core.domain.repository

import com.kajangdev.core.data.Resource
import com.kajangdev.core.domain.model.Movie
import com.kajangdev.core.domain.model.Slides
import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun getMovie(): Flow<Resource<List<Movie>>>

    fun getTv(): Flow<Resource<List<Movie>>>

    fun getMovieSearch(query: String): Flow<Resource<List<Movie>>>

    fun getTvSearch(query: String): Flow<Resource<List<Movie>>>

    fun insertItem(movie: Movie)

    fun deleteItem(id: Int)

    fun getBookmarkMovie(): Flow<List<Movie>>

    fun getBookmarkTv(): Flow<List<Movie>>

    fun checkBookmark(id: Int): Flow<Movie>

    fun getSlides(): Flow<Resource<List<Slides>>>

}