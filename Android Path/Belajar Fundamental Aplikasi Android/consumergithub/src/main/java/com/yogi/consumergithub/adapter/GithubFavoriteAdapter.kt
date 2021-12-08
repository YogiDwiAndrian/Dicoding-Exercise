package com.yogi.consumergithub.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yogi.consumergithub.R
import com.yogi.consumergithub.models.Github
import com.yogi.githubmedia.utils.ImgConvert.bitmapToImage
import kotlinx.android.synthetic.main.item_rv_favorite.view.*

class GithubFavoriteAdapter(mCtx : Context, var githubList : ArrayList<Github>): RecyclerView.Adapter<GithubFavoriteAdapter.GithubFavoriteViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GithubFavoriteAdapter.GithubFavoriteViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_favorite, parent, false)
        return GithubFavoriteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return githubList.size
    }

    override fun onBindViewHolder(
        holder: GithubFavoriteAdapter.GithubFavoriteViewHolder,
        position: Int
    ) {
        val github : Github = githubList[position]

        Glide.with(holder.itemView.context)
            .load(bitmapToImage(github.avatarFV!!))
            .placeholder(R.drawable.avatar_load)
            .error(R.drawable.ic_baseline_broken_image_24)
            .apply(RequestOptions())
            .into(holder.imgAvatar)

        holder.tvUsers.text = github.username

        holder.btnRepo.setOnClickListener {
            val webIntent: Intent = Uri.parse(github.url_html).let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }

            holder.itemView.context.startActivity(webIntent)
        }
    }

    inner class GithubFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvUsers: TextView = itemView.tv_name_fv
        var imgAvatar: ImageView = itemView.img_users_fv
        var btnRepo: TextView = itemView.btn_repo_url_fv
    }
}