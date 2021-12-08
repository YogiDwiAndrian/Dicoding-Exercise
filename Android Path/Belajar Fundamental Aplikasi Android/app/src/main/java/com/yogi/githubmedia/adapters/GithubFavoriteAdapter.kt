package com.yogi.githubmedia.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yogi.githubmedia.R
import com.yogi.githubmedia.data.database.GithubHelper
import com.yogi.githubmedia.models.Github
import com.yogi.githubmedia.utils.ImgConvert.bitmapToImage
import kotlinx.android.synthetic.main.item_rv_favorite.view.*

class GithubFavoriteAdapter(mCtx: Context, private val githubList: ArrayList<Github>) :
    RecyclerView.Adapter<GithubFavoriteAdapter.GithubFavoriteViewHolder>() {

    private val githubHelper = GithubHelper.getInstance(mCtx)

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
        val github: Github = githubList[position]

        Glide.with(holder.itemView.context)
            .load(bitmapToImage(github.avatarFV!!))
            .placeholder(R.drawable.avatar_load)
            .error(R.drawable.ic_baseline_broken_image_24)
            .apply(RequestOptions())
            .into(holder.imgAvatar)

        holder.tvUsers.text = github.username

        holder.btnRepo.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(github.url_html))

            holder.itemView.context.startActivity(webIntent)
        }

        holder.btnUnFv.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle(R.string.warning)
                .setMessage(R.string.msg_remove)
                .setPositiveButton(R.string.yes) { _, _ ->
                    if (githubHelper.deleteById(github.id.toString())) {
                        githubList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, githubList.size)
                    } else {
                        Toast.makeText(
                            holder.itemView.context,
                            R.string.msg_remove_error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .setNegativeButton(
                    R.string.no
                ) { _, _ -> }
                .setIcon(R.drawable.ic_baseline_warning_24)
                .show()
        }
    }

    inner class GithubFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvUsers: TextView = itemView.tv_name_fv
        var imgAvatar: ImageView = itemView.img_users_fv
        var btnRepo: TextView = itemView.btn_repo_url_fv
        var btnUnFv: ImageView = itemView.btn_unFv
    }
}