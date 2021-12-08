package com.kajangdevs.netplay.ui.tvshow

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
import com.kajangdevs.netplay.utils.Constant.getGenreNameTv
import com.kajangdevs.netplay.utils.checkNull
import kotlinx.android.synthetic.main.item_rv_vertical.view.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {

        override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
            return oldItem.tvShowId == newItem.tvShowId
        }

        override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_vertical, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<TvShowEntity>) {
        differ.submitList(list)
        notifyDataSetChanged()
    }

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvshow: TvShowEntity) {
            with(itemView) {
                tv_tittle.text = tvshow.title
                ratebar.rating = tvshow.rating / 2
                ratenum.text = tvshow.rating.toString()
                tv_release_year.text = tvshow.released
                tv_genre.text = getGenreNameTv(tvshow.genreId).checkNull()

                setOnClickListener {
                    val array = arrayListOf<Int>()
                    array.addAll(tvshow.genreId)
                    val intent = Intent(context, DetailTvShowActivity::class.java)
                    intent.putExtra(EXTRA_TVSHOWS, tvshow.tvShowId)
                    intent.putIntegerArrayListExtra(EXTRA_ARRAY, array)
                    context.startActivity(intent)
                }

                Glide.with(context)
                    .load(tvshow.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_poster)
            }
        }
    }
}