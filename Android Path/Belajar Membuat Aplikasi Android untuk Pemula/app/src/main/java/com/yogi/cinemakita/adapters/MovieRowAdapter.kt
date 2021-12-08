package com.yogi.cinemakita.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yogi.cinemakita.R
import com.yogi.cinemakita.models.Movie
import com.yogi.cinemakita.ui.detail.MovieDetailActivity
import kotlinx.android.synthetic.main.item_rvrow_movie.view.*

class MovieRowAdapter (private val listMovie: ArrayList<Movie>) : RecyclerView.Adapter<MovieRowAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_rvrow_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movies = listMovie[position]
        Glide.with(holder.itemView.context)
            .load(movies.poster)
            .apply(RequestOptions())
            .into(holder.imgPoster)
        holder.tvTittle.text = movies.tittle
        holder.tvRelease.text = movies.release.toString()
        holder.tvGenres.text = movies.genre
        holder.tvRateStar.rating = movies.rating / 2
        holder.vidPreview.loadUrl(movies.preview)

        holder.itemView.setOnClickListener {
            val moveWithObjectIntent = Intent(holder.mContext, MovieDetailActivity::class.java)

            moveWithObjectIntent.putExtra(MovieDetailActivity.DATA_MOVIES, movies)
            holder.mContext.startActivity(moveWithObjectIntent)
        }

    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mContext: Context = itemView.context
        var imgPoster: ImageView = itemView.img_poster_movie
        var tvTittle: TextView = itemView.tv_tittle_movie
        var tvRelease: TextView = itemView.tv_release_movie
        var tvRateStar: RatingBar = itemView.ratingBar_star_movie
        var tvGenres: TextView = itemView.tv_genres_movie
        val vidPreview: WebView = itemView.vid_background_movie
    }

}