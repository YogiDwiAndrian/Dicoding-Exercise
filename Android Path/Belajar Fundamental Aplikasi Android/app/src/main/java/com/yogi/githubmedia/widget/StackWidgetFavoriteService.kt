package com.yogi.githubmedia.widget

import android.content.Intent
import android.widget.RemoteViewsService

class StackWidgetFavoriteService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory =
        StackWidgetRemote(this.applicationContext)
}