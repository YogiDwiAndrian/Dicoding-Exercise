package com.dicoding.picodiploma.helper.consumerapp

import android.database.Cursor
import com.yogi.consumergithub.models.Github
import com.yogi.githubmedia.data.database.DatabaseContract
import java.util.*

object MappingHelper {

    fun mapCursorToArrayList(cursor: Cursor?): ArrayList<Github> {
        val githubList = ArrayList<Github>()
        cursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.GithubColumns.ID))
                val avatar = getBlob(getColumnIndexOrThrow(DatabaseContract.GithubColumns.IMAGE))
                val username = getString(getColumnIndexOrThrow(DatabaseContract.GithubColumns.USERNAME))
                val url = getString(getColumnIndexOrThrow(DatabaseContract.GithubColumns.HTML_URL))
                githubList.add(Github(id, avatar, username, url))
            }
        }
        return githubList
    }
}
