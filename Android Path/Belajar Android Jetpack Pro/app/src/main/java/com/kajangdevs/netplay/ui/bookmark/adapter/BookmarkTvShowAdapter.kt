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
import com.kajangdevs.netplay.data.source.local.room.entity.TvShowBookmarkEntity
import com.kajangdevs.netplay.ui.detail.DetailTvShowActivity
import com.kajangdevs.netplay.utils.checkNull
import kotlinx.android.synthetic.main.item_rv_vertical.view.*

class BookmarkTvShowAdapter :
    PagedListAdapter<TvShowBookmarkEntity, BookmarkTvShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowBookmarkEntity>() {

            override fun areItemsTheSame(
                oldItem: TvShowBookmarkEntity,
                newItem: TvShowBookmarkEntity
            ): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(
                oldItem: TvShowBookmarkEntity,
                newItem: TvShowBookmarkEntity
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
        fun bind(tvShow: TvShowBookmarkEntity) {
            with(itemView) {
                tv_tittle.text = tvShow.title
                ratebar.rating = tvShow.rating / 2
                ratenum.text = tvShow.rating.toString()
                tv_release_year.text = tvShow.released
                tv_genre.text = tvShow.genre.checkNull()

                setOnClickListener {
                    val intent = Intent(context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.BOOKMARK_TVSHOW, tvShow.tvShowId)
                    intent.putExtra(DetailTvShowActivity.EXTRA_GENRE, tvShow.genre)
                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load(tvShow.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_poster)
            }
        }

    }

}