package com.yogi.githubmedia.data.database

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {

    const val AUTHORITY = "com.yogi.githubmedia"
    const val SCHEME = "content"

    internal class GithubColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "github"
            const val ID = "id"
            const val IMAGE = "image"
            const val USERNAME = "username"
            const val HTML_URL = "html_url"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}