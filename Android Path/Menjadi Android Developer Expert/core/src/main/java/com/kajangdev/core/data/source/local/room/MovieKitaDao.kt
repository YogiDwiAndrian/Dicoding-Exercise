package com.kajangdev.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kajangdev.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieKitaDao {
    @Query("SELECT * FROM movie where isTvShow = 0")
    fun getBookmarkMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where isTvShow = 1")
    fun getBookmarkTv(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(movie: MovieEntity)

    @Query("DELETE FROM movie where id = :id")
    fun deleteItem(id: Int)

    @Query("SELECT * FROM movie where id = :id")
    fun checkBookmark(id: Int): Flow<MovieEntity>

}