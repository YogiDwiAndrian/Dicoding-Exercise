package com.yogi.githubmedia.widget

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.bumptech.glide.Glide
import com.yogi.githubmedia.R
import com.yogi.githubmedia.data.database.GithubHelper
import com.yogi.githubmedia.models.Github

class StackWidgetRemote(context: Context) : RemoteViewsService.RemoteViewsFactory {

    private lateinit var githubHelper: GithubHelper
    private var mContext: Context = context
    private lateinit var githubList: ArrayList<Github>

    override fun onCreate() {
    }

    override fun onDataSetChanged() {
        githubHelper = GithubHelper.getInstance(mContext)
        githubList = githubHelper.getFavorite(mContext)
    }

    override fun onDestroy() {

    }

    override fun getCount(): Int = githubList.size

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(mContext.packageName, R.layout.widget_user_favorite_item)

        try {
            val bitmap: Bitmap = Glide.with(mContext)
                .asBitmap()
                .load(githubList[position].avatarFV)
                .submit()
                .get()
            rv.setImageViewBitmap(R.id.img_favorite_user_item, bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val extras = Bundle()
        extras.putInt(FavoriteUserWidget.EXTRA_ITEM, position)

        val fillIntent = Intent()
        fillIntent.putExtras(extras)

        rv.setOnClickFillInIntent(R.id.widget_item, fillIntent)
        return rv
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(i: Int): Long = 0

    override fun hasStableIds(): Boolean = false
}