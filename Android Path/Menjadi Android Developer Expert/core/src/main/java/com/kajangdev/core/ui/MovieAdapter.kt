package com.kajangdev.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kajangdev.core.R
import com.kajangdev.core.databinding.ItemRvVerticalBinding
import com.kajangdev.core.domain.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null

    private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
                ItemRvVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
        if (movie != null) {
            holder.bind(movie)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(newListData: List<Movie>?) {
        differ.submitList(newListData)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemRvVerticalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                tvTitleVer.text = movie.title
                rateBarVer.rating = movie.rating ?: 0.0f / 2
                tvRateNumVer.text = movie.rating.toString()
                tvRelease.text = movie.released
                tvGenre.text = movie.genre

                Glide.with(itemView.context)
                        .load(movie.poster)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.loading_poster)
                                        .error(R.drawable.error_poster)
                        )
                        .into(ivPosterVer)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(differ.currentList[adapterPosition])
            }
        }
    }
}