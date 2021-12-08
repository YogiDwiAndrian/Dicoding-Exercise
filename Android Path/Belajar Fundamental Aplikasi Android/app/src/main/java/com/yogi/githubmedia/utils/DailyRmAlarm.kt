package com.yogi.githubmedia.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

object DailyRmAlarm {

    fun setRepeatingAlarm(context: Context, title: String, message: String) {


        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyRmReceiver::class.java)
        intent.putExtra(DailyRmReceiver.TITLE, title)
        intent.putExtra(DailyRmReceiver.MESSAGE, message)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 9)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            DailyRmReceiver.ID_REPEATING, intent, 0
        )
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyRmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            DailyRmReceiver.ID_REPEATING, intent, 0
        )
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)
    }
}