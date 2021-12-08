package com.kajangdevs.netplay.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kajangdevs.netplay.R
import com.kajangdevs.netplay.data.source.local.entity.HastagEntitiy
import kotlinx.android.synthetic.main.item_hastag.view.*
import java.util.*

class HastagAdapter : RecyclerView.Adapter<HastagAdapter.HastagViewHolder>() {

    private var listhastag = ArrayList<HastagEntitiy>()

    fun setMovies(hastags: List<HastagEntitiy>?) {
        if (hastags == null) return
        this.listhastag.clear()
        this.listhastag.addAll(hastags)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HastagViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hastag, parent, false)
        return HastagViewHolder(view)
    }

    override fun onBindViewHolder(holder: HastagViewHolder, position: Int) {
        val hastag = listhastag[position]
        holder.bind(hastag)
    }

    override fun getItemCount(): Int = listhastag.size

    class HastagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(hastag: HastagEntitiy) {
            with(itemView) {
                tv_hastag.text = hastag.hastag
                Glide.with(context)
                    .load(hastag.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_background)
            }
        }
    }
}