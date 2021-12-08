package com.kajangdevs.netplay.data.source.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kajangdevs.netplay.data.source.local.room.entity.MovieBookmarkEntity
import com.kajangdevs.netplay.data.source.local.room.entity.TvShowBookmarkEntity

@Dao
interface BookmarkDao {

    @Insert
    suspend fun insertMv(movie: MovieBookmarkEntity)

    @Insert
    suspend fun insertTv(movie: TvShowBookmarkEntity)

    @Query("SELECT * FROM moviebookmark")
    fun getBookmarkMovies(): DataSource.Factory<Int, MovieBookmarkEntity>

    @Query("SELECT * FROM tvshowbookmark")
    fun getBookmarkTvShow(): DataSource.Factory<Int, TvShowBookmarkEntity>

    @Query("SELECT * FROM moviebookmark WHERE id = :id")
    fun getBookmarkMovieById(id: Int): MovieBookmarkEntity?

    @Query("SELECT * FROM tvshowbookmark WHERE id = :id")
    fun getBookmarkTvShowById(id: Int): TvShowBookmarkEntity?

    @Query("DELETE FROM moviebookmark WHERE id = :id")
    suspend fun deleteMv(id: Int)

    @Query("DELETE FROM tvshowbookmark WHERE id = :id")
    suspend fun deleteTv(id: Int)
}