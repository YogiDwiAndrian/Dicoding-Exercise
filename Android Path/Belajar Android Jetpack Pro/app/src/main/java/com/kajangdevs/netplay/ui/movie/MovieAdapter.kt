package com.kajangdevs.netplay.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kajangdevs.netplay.R
import com.kajangdevs.netplay.data.source.local.entity.MovieEntity
import com.kajangdevs.netplay.ui.detail.DetailMovieActivity
import com.kajangdevs.netplay.utils.Constant.getGenreNameMv
import com.kajangdevs.netplay.utils.checkNull
import kotlinx.android.synthetic.main.item_rv_vertical.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {

        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_vertical, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<MovieEntity>) {
        differ.submitList(list)
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieEntity) {
            with(itemView) {
                tv_tittle.text = movie.title
                ratebar.rating = movie.rating / 2
                ratenum.text = movie.rating.toString()
                tv_release_year.text = movie.released
                tv_genre.text = getGenreNameMv(movie.genreId).checkNull()

                setOnClickListener {
                    val array = arrayListOf<Int>()
                    array.addAll(movie.genreId)
                    val intent = Intent(context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIES, movie.movieId)
                    intent.putIntegerArrayListExtra(DetailMovieActivity.EXTRA_ARRAY, array)
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