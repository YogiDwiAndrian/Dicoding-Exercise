package com.kajangdev.core.data.source.local

import com.kajangdev.core.data.source.local.entity.MovieEntity
import com.kajangdev.core.data.source.local.room.MovieKitaDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieKitaDao: MovieKitaDao) {

    fun getBookmarkMovie(): Flow<List<MovieEntity>> = movieKitaDao.getBookmarkMovie()

    fun getBookmarkTv(): Flow<List<MovieEntity>> = movieKitaDao.getBookmarkTv()

    suspend fun insertItem(movie: MovieEntity) = movieKitaDao.insertItem(movie)

    fun deleteItem(id: Int) = movieKitaDao.deleteItem(id)

    fun checkBookmark(id: Int): Flow<MovieEntity> = movieKitaDao.checkBookmark(id)

}