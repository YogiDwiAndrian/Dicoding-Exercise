package com.yogi.githubmedia.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.yogi.githubmedia.data.database.DatabaseContract.GithubColumns.Companion.TABLE_NAME

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "dbgithubmedia"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_GITHUB = "CREATE TABLE $TABLE_NAME" +
                " (${DatabaseContract.GithubColumns.ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${DatabaseContract.GithubColumns.IMAGE} BLOB," +
                " ${DatabaseContract.GithubColumns.USERNAME} TEXT," +
                " ${DatabaseContract.GithubColumns.HTML_URL} TEXT)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_GITHUB)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}