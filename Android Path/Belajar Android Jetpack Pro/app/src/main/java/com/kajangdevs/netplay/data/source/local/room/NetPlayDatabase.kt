package com.kajangdevs.netplay.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kajangdevs.netplay.data.source.local.room.entity.MovieBookmarkEntity
import com.kajangdevs.netplay.data.source.local.room.entity.TvShowBookmarkEntity

@Database(
    entities = [MovieBookmarkEntity::class, TvShowBookmarkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NetPlayDatabase : RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDao

    companion object {

        @Volatile
        private var INSTANCE: NetPlayDatabase? = null

        fun getInstance(context: Context): NetPlayDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    NetPlayDatabase::class.java,
                    "NetPlay.db"
                ).build()
            }
    }
}