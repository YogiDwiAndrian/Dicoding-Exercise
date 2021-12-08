package com.kajangdevs.netplay.ui.bookmark.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kajangdevs.netplay.R
import com.kajangdevs.netplay.data.source.local.room.entity.MovieBookmarkEntity
import com.kajangdevs.netplay.ui.detail.DetailMovieActivity
import com.kajangdevs.netplay.ui.detail.DetailMovieActivity.Companion.BOOKMARK_MOVIES
import com.kajangdevs.netplay.ui.detail.DetailMovieActivity.Companion.EXTRA_GENRE
import com.kajangdevs.netplay.utils.checkNull
import kotlinx.android.synthetic.main.item_rv_vertical.view.*

class BookmarkMovieAdapter :
    PagedListAdapter<MovieBookmarkEntity, BookmarkMovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieBookmarkEntity>() {

            override fun areItemsTheSame(
                oldItem: MovieBookmarkEntity,
                newItem: MovieBookmarkEntity
            ): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(
                oldItem: MovieBookmarkEntity,
                newItem: MovieBookmarkEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieBookmarkEntity) {
            with(itemView) {
                tv_tittle.text = movie.title
                ratebar.rating = movie.rating / 2
                ratenum.text = movie.rating.toString()
                tv_release_year.text = movie.released
                tv_genre.text = movie.genre.checkNull()

                setOnClickListener {
                    val intent = Intent(context, DetailMovieActivity::class.java)
                    intent.putExtra(BOOKMARK_MOVIES, movie.movieId)
                    intent.putExtra(EXTRA_GENRE, movie.genre)
                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load(movie.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_poster)
            }
        }

    }
}