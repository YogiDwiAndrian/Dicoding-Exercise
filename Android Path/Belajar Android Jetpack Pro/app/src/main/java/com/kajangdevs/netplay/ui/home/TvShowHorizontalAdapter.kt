package com.kajangdevs.netplay.ui.home

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
import com.kajangdevs.netplay.data.source.local.entity.TvShowEntity
import com.kajangdevs.netplay.ui.detail.DetailTvShowActivity
import com.kajangdevs.netplay.ui.detail.DetailTvShowActivity.Companion.EXTRA_ARRAY
import com.kajangdevs.netplay.ui.detail.DetailTvShowActivity.Companion.EXTRA_TVSHOWS
import kotlinx.android.synthetic.main.item_rv_horizontal.view.*

class TvShowHorizontalAdapter :
    RecyclerView.Adapter<TvShowHorizontalAdapter.TvShowHorizontalViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {

        override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
            return oldItem.tvShowId == newItem.tvShowId
        }

        override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowHorizontalViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_horizontal, parent, false)
        return TvShowHorizontalViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowHorizontalViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<TvShowEntity>) {
        differ.submitList(list)
    }

    class TvShowHorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShowEntity) {
            with(itemView) {
                tv_tittle_hz.text = tvShow.title
                ratebar_hz.rating = tvShow.rating / 2
                ratenum_hz.text = tvShow.rating.toString()
                setOnClickListener {
                    val array = arrayListOf<Int>()
                    array.addAll(tvShow.genreId)
                    val intent = Intent(context, DetailTvShowActivity::class.java)
                    intent.putExtra(EXTRA_TVSHOWS, tvShow.tvShowId)
                    intent.putIntegerArrayListExtra(EXTRA_ARRAY, array)
                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load(tvShow.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_poster_hz)
            }
        }
    }
}