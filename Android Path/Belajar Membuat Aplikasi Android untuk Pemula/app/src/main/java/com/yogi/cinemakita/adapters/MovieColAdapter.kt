package com.yogi.cinemakita.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yogi.cinemakita.R
import com.yogi.cinemakita.models.Movie
import com.yogi.cinemakita.ui.detail.MovieDetailActivity
import kotlinx.android.synthetic.main.item_rvcol_movie.view.*

class MovieColAdapter (private val listMovie: ArrayList<Movie>) : RecyclerView.Adapter<MovieColAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_rvcol_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movies = listMovie[position]
        Glide.with(holder.itemView.context)
            .load(movies.poster)
            .apply(RequestOptions())
            .into(holder.imgPoster)
        holder.tvRateNum.text = movies.rating.toString()
        holder.tvTittle.text = movies.tittle
        holder.tvRelease.text = movies.release.toString()

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
        var imgPoster: ImageView = itemView.img_poster_movieCol
        var tvRateNum: TextView = itemView.tv_rateNum_movieCol
        var tvTittle: TextView = itemView.tv_tittle_movieCol
        var tvRelease: TextView = itemView.tv_release_movieCol
    }
}