package com.kajangdev.core.data.source.remote

import android.util.Log
import com.kajangdev.core.data.source.remote.network.ApiResponse
import com.kajangdev.core.data.source.remote.network.ApiService
import com.kajangdev.core.data.source.remote.response.MovieResponse
import com.kajangdev.core.data.source.remote.response.TvResponse
import com.kajangdev.core.utils.Constant.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovies(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSourceMv", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTv(): Flow<ApiResponse<List<TvResponse>>> {
        return flow {
            try {
                val response = apiService.getTvShow(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSourceTv", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieSearch(query: String): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieSearch(API_KEY, query)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSourceMv", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvSearch(query: String): Flow<ApiResponse<List<TvResponse>>> {
        return flow {
            try {
                val response = apiService.getTvShowSearch(API_KEY, query)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSourceMv", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}