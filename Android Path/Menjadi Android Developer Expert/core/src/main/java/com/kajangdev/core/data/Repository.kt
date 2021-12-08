package com.kajangdev.core.data

import com.kajangdev.core.data.source.local.LocalDataSource
import com.kajangdev.core.data.source.remote.RemoteDataSource
import com.kajangdev.core.data.source.remote.network.ApiResponse
import com.kajangdev.core.domain.model.Movie
import com.kajangdev.core.domain.model.Slides
import com.kajangdev.core.domain.repository.IRepository
import com.kajangdev.core.utils.AppExecutors
import com.kajangdev.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class Repository(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) : IRepository {

    override fun getMovie(): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getMovie().first()) {
                is ApiResponse.Success -> {
                    val newsList = DataMapper.movieResponsesToDomain(apiResponse.data)
                    emit(Resource.Success(newsList))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Error<List<Movie>>("Empty", null))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error<List<Movie>>(apiResponse.errorMessage))
                }
            }
        }
    }

    override fun getTv(): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getTv().first()) {
                is ApiResponse.Success -> {
                    val newsList = DataMapper.tvResponsesToDomain(apiResponse.data)
                    emit(Resource.Success(newsList))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Error<List<Movie>>("Empty", null))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error<List<Movie>>(apiResponse.errorMessage))
                }
            }
        }
    }

    override fun getMovieSearch(query: String): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getMovieSearch(query).first()) {
                is ApiResponse.Success -> {
                    val movieList = DataMapper.movieResponsesToDomain(apiResponse.data)
                    emit(Resource.Success(movieList))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Error<List<Movie>>("Empty", null))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error<List<Movie>>(apiResponse.errorMessage))
                }
            }
        }
    }

    override fun getTvSearch(query: String): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getTvSearch(query).first()) {
                is ApiResponse.Success -> {
                    val tvList = DataMapper.tvResponsesToDomain(apiResponse.data)
                    emit(Resource.Success(tvList))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Error<List<Movie>>("Empty", null))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error<List<Movie>>(apiResponse.errorMessage))
                }
            }
        }
    }

    override fun insertItem(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            val movieEntity = DataMapper.mapDomainToEntity(movie)
            localDataSource.insertItem(movieEntity)
        }
    }

    override fun deleteItem(id: Int) {
        appExecutors.diskIO().execute { localDataSource.deleteItem(id) }
    }

    override fun getBookmarkMovie(): Flow<List<Movie>> {
        return localDataSource.getBookmarkMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getBookmarkTv(): Flow<List<Movie>> {
        return localDataSource.getBookmarkTv().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun checkBookmark(id: Int): Flow<Movie> {
        return localDataSource.checkBookmark(id).map { DataMapper.mapEntityToDomain(it) }
    }

    override fun getSlides(): Flow<Resource<List<Slides>>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getMovie().first()) {
                is ApiResponse.Success -> {
                    val newsList = DataMapper.responseToSlides(apiResponse.data)
                    emit(Resource.Success(newsList))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Error<List<Slides>>("Empty", null))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error<List<Slides>>(apiResponse.errorMessage))
                }
            }
        }
    }

}
