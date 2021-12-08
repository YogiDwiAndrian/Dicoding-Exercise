package com.yogi.githubmedia.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yogi.githubmedia.R
import com.yogi.githubmedia.models.Github
import com.yogi.githubmedia.ui.detail.GithubDetailActivity
import kotlinx.android.synthetic.main.item_rv_fragment.view.*

class GithubFragmentAdapter(private val listGithub: ArrayList<Github>) :
    RecyclerView.Adapter<GithubFragmentAdapter.GithubViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_fragment, parent, false)
        return GithubViewHolder(view)
    }

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {

        val github = listGithub[position]
        Glide.with(holder.itemView.context)
            .load(github.avatar)
            .placeholder(R.drawable.avatar_load)
            .error(R.drawable.ic_baseline_broken_image_24)
            .apply(RequestOptions())
            .into(holder.imgAvatar)
        holder.tvUsers.text = github.username

        holder.itemView.setOnClickListener {
            val moveWithObjectIntent = Intent(holder.mContext, GithubDetailActivity::class.java)

            moveWithObjectIntent.putExtra(GithubDetailActivity.DATA_GITHUB, github)
            holder.mContext.startActivity(moveWithObjectIntent)
        }

    }

    override fun getItemCount(): Int {
        return listGithub.size
    }

    inner class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mContext: Context = itemView.context
        var imgAvatar: ImageView = itemView.img_users_frag
        var tvUsers: TextView = itemView.tv_usrnm_frag
    }
}