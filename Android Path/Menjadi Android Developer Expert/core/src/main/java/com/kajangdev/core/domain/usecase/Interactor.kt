package com.kajangdev.core.domain.usecase

import com.kajangdev.core.data.Resource
import com.kajangdev.core.domain.model.Movie
import com.kajangdev.core.domain.model.Slides
import com.kajangdev.core.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class Interactor(private val iRepository: IRepository) : UseCase {
    override fun getMovie(): Flow<Resource<List<Movie>>> = iRepository.getMovie()

    override fun getTv(): Flow<Resource<List<Movie>>> = iRepository.getTv()

    override fun getMovieSearch(query: String): Flow<Resource<List<Movie>>> = iRepository.getMovieSearch(query)

    override fun getTvSearch(query: String): Flow<Resource<List<Movie>>> = iRepository.getTvSearch(query)

    override fun getBookmarkMovie(): Flow<List<Movie>> = iRepository.getBookmarkMovie()

    override fun getBookmarkTv(): Flow<List<Movie>> = iRepository.getBookmarkTv()

    override fun insertItem(movie: Movie) = iRepository.insertItem(movie)

    override fun deleteItem(id: Int) = iRepository.deleteItem(id)

    override fun checkBookmark(id: Int): Flow<Movie> = iRepository.checkBookmark(id)

    override fun getSlides(): Flow<Resource<List<Slides>>> = iRepository.getSlides()
}