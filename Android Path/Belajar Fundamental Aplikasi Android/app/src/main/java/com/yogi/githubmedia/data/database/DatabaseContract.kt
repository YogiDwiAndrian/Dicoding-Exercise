package com.yogi.githubmedia.data.database

import android.provider.BaseColumns

object DatabaseContract {

    internal class GithubColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "github"
            const val ID = "id"
            const val IMAGE = "image"
            const val USERNAME = "username"
            const val HTML_URL = "html_url"
        }
    }
}