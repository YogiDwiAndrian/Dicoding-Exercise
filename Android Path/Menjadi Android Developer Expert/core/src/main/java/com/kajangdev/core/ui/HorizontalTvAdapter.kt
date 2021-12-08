package com.kajangdev.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kajangdev.core.R
import com.kajangdev.core.databinding.ItemRvHorizontalBinding
import com.kajangdev.core.domain.model.Movie

class HorizontalTvAdapter : RecyclerView.Adapter<HorizontalTvAdapter.HorizontalTvViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalTvViewHolder {
        val view =
                ItemRvHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HorizontalTvViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorizontalTvViewHolder, position: Int) {
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

    inner class HorizontalTvViewHolder(private val binding: ItemRvHorizontalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                tvTitle.text = movie.title
                rateBarHor.rating = movie.rating ?: 0.0f / 2
                tvRateNumHor.text = movie.rating.toString()

                Glide.with(itemView.context)
                        .load(movie.poster)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.loading_poster)
                                        .error(R.drawable.error_poster)
                        )
                        .into(ivPosterHor)
            }

            binding.root.setOnClickListener {
                onItemClick?.let {
                    it(movie)
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(differ.currentList[adapterPosition])
            }
        }
    }
}