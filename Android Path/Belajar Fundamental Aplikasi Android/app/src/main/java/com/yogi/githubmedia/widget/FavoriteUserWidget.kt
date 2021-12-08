package com.yogi.githubmedia.widget

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.net.toUri
import com.yogi.githubmedia.R
import com.yogi.githubmedia.ui.menu.FavoriteActivity

class FavoriteUserWidget : AppWidgetProvider() {

    companion object {

        private const val TOAST_ACTION = "com.yogi.githubmedia.TOAST_ACTION"
        const val EXTRA_ITEM = "com.yogi.githubmedia.EXTRA_ITEM"

        private fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val views = RemoteViews(context.packageName, R.layout.favorite_user_widget)

            val intent = Intent(context, StackWidgetFavoriteService::class.java)
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(Intent.URI_INTENT_SCHEME).toUri()
            views.setRemoteAdapter(R.id.stack_view, intent)

            val clickWidget = Intent(context, FavoriteActivity::class.java)
            val clickPendingWidget = TaskStackBuilder.create(context)
                .addNextIntentWithParentStack(clickWidget)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            views.setPendingIntentTemplate(R.id.stack_view, clickPendingWidget)

            val updateWidget = Intent(context, FavoriteUserWidget::class.java)
            updateWidget.action = TOAST_ACTION
            updateWidget.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)

            val updatePendingWidget = PendingIntent.getBroadcast(
                context,
                0,
                updateWidget,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            views.setOnClickPendingIntent(R.id.btn_refresh_widget, updatePendingWidget)

            views.setEmptyView(R.id.stack_view, R.id.empty_view)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action != null) {
            if (intent.action == TOAST_ACTION) {
                val appWidgetManager = AppWidgetManager.getInstance(context)
                val thisWidget = ComponentName(context, FavoriteUserWidget::class.java)
                val appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.stack_view)
            }
        }
    }
}