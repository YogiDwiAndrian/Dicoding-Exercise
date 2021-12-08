package com.yogi.githubmedia.data.database

import android.content.ContentValues
import android.content.Context
import android.content.res.Resources
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.util.Log
import android.widget.Toast
import com.yogi.githubmedia.R
import com.yogi.githubmedia.data.database.DatabaseContract.GithubColumns.Companion.HTML_URL
import com.yogi.githubmedia.data.database.DatabaseContract.GithubColumns.Companion.ID
import com.yogi.githubmedia.data.database.DatabaseContract.GithubColumns.Companion.IMAGE
import com.yogi.githubmedia.data.database.DatabaseContract.GithubColumns.Companion.TABLE_NAME
import com.yogi.githubmedia.data.database.DatabaseContract.GithubColumns.Companion.USERNAME
import com.yogi.githubmedia.models.Github
import java.sql.SQLException

class GithubHelper(context: Context) {

    private var dataBaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private var INSTANCE: GithubHelper? = null

        fun getInstance(context: Context): GithubHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: GithubHelper(context)
            }
    }

    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }

    fun close() {
        dataBaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor {
        val qry = "Select * From $TABLE_NAME"
        open()
        return database.rawQuery(qry, null)
    }

    fun queryById(id: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "${BaseColumns._ID} = ?",
            arrayOf(id),
            null,
            null,
            null,
            null
        )
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun getFavorite(mCtx: Context): ArrayList<Github> {
        val qry = "Select * From $TABLE_NAME"
        open()
        val cursor = database.rawQuery(qry, null)
        val githubList = ArrayList<Github>()

        if (cursor.count == 0) {
            Toast.makeText(mCtx, R.string.favorite_noFound, Toast.LENGTH_SHORT).show()
        } else {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val github = Github()
                github.id = cursor.getInt(cursor.getColumnIndex(ID))
                github.avatarFV = cursor.getBlob(cursor.getColumnIndex(IMAGE))
                github.username = cursor.getString(cursor.getColumnIndex(USERNAME))
                github.url_html = cursor.getString(cursor.getColumnIndex(HTML_URL))
                githubList.add(github)
                cursor.moveToNext()
            }
        }
        cursor.close()
        close()
        return githubList
    }

    fun username(username: String): String {
        val users: String
        val qry = "Select * From $TABLE_NAME Where $USERNAME = '$username'"
        open()
        val cursor = database.rawQuery(qry, null)
        users = cursor.count.toString()
        cursor.close()
        close()
        return users
    }

    fun deleteById(id: String): Boolean {
        open()
        var result = false
        try {
            database.delete(DATABASE_TABLE, "$ID = '$id'", null)
            result = true
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, Resources.getSystem().getString(R.string.msg_remove_error))
        }
        close()
        return result
    }

    fun deleteByUsername(username: String): Boolean {
        open()
        var result = false
        try {
            database.delete(DATABASE_TABLE, "$USERNAME = '$username'", null)
            result = true
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, Resources.getSystem().getString(R.string.msg_remove_error))
        }
        close()
        return result
    }


}