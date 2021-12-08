package com.yogi.consumergithub.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.helper.consumerapp.MappingHelper
import com.yogi.consumergithub.R
import com.yogi.consumergithub.adapter.GithubFavoriteAdapter
import com.yogi.githubmedia.data.database.DatabaseContract.GithubColumns.Companion.CONTENT_URI
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        viewFavorite()
    }

    private fun viewFavorite(){
        val cursor = contentResolver?.query(CONTENT_URI, null, null, null, null)
        val githubList = MappingHelper.mapCursorToArrayList(cursor)
        cursor?.close()

        val adapter = GithubFavoriteAdapter(this, githubList)
        val rv : RecyclerView = rv_favorite
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.adapter = adapter

    }
}