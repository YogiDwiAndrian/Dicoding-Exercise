package com.yogi.githubmedia.ui.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogi.githubmedia.R
import com.yogi.githubmedia.adapters.GithubFavoriteAdapter
import com.yogi.githubmedia.data.database.GithubHelper
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    private lateinit var githubHelper: GithubHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        githubHelper = GithubHelper.getInstance(applicationContext)

        viewFavorite()
    }

    private fun viewFavorite() {
        val githubList = githubHelper.getFavorite(this)

        val adapter = GithubFavoriteAdapter(this, githubList)
        val rv: RecyclerView = rv_favorite
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.adapter = adapter
    }
}