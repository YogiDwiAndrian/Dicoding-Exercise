package com.yogi.cinemakita.ui.detail

import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yogi.cinemakita.R
import com.yogi.cinemakita.adapters.MovieColAdapter
import com.yogi.cinemakita.data.MovieData
import com.yogi.cinemakita.models.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity(){

    companion object {
        const val DATA_MOVIES = "data_movies"
    }

    //recyclerview horizontal
    private lateinit var rvMovieCol: RecyclerView
    private var list: ArrayList<Movie> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movies = intent.getParcelableExtra(DATA_MOVIES) as Movie

        //change webview
        val vidPreview: WebView = webView
        vidPreview.loadUrl(movies.preview)

        //change tittle
        tv_tittle_moviedetail.text = movies.tittle

        //change poster
        Glide.with(img_poster_moviedetail)
            .load(movies.poster)
            .apply(RequestOptions())
            .into(img_poster_moviedetail)

        //change release
        tv_release_value_moviedetail.text = movies.release.toString()

        //change synopsis
        tv_synopsis_value_moviedetail.text = movies.synopsis

        //change genres
        tv_genres_value_moviedetail.text = movies.genre

        //change ratingBar
        rating_star_moviedetail.rating = movies.rating / 2

        //change rateNum
        rating_num_moviedetail.text = movies.rating.toString()


        rvMovieCol = findViewById(R.id.rv_movie_col)
        rvMovieCol.setHasFixedSize(true)

        list.addAll(MovieData.listData)
        showRecyclerCardView()
    }

    private fun showRecyclerCardView() {
        rvMovieCol.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val cardViewColAdapter = MovieColAdapter(list)
        rvMovieCol.adapter = cardViewColAdapter

    }
}