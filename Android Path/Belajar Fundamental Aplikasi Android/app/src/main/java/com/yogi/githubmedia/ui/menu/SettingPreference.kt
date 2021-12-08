package com.yogi.githubmedia.ui.menu

import android.content.Context
import android.content.SharedPreferences

class SettingPreference {
    companion object {
        private const val PREFS_NAME = "github_setting"
        const val STATS_DAILY = "daily_reminder"
    }

    private var preferences: SharedPreferences? = null

    fun setPreference(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun setDailyReminder(value: Boolean?) {
        val editor = preferences?.edit()
        editor?.putBoolean(STATS_DAILY, value!!)
        editor?.apply()
    }

    fun getDailyReminder(): Boolean? {
        return preferences?.getBoolean(STATS_DAILY, false)
    }
}