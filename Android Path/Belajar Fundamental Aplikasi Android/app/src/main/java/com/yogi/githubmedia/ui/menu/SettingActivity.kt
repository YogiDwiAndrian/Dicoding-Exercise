package com.yogi.githubmedia.ui.menu

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.yogi.githubmedia.R
import com.yogi.githubmedia.ui.MainActivity
import com.yogi.githubmedia.utils.DailyRmAlarm
import kotlinx.android.synthetic.main.activity_setting.*
import java.util.*


@Suppress("DEPRECATION")
class SettingActivity : AppCompatActivity() {
    private lateinit var locale: Locale
    private lateinit var dailyRmAlarm: DailyRmAlarm
    private var preference: SettingPreference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        getSwitchLanguage()
        setLanguage()

        preference = SettingPreference()
        preference?.setPreference(this)
        sw_dailiy_reminder.isChecked = preference?.getDailyReminder()!!

        getSwitchRm()
        btnSaveConfig()
        dailyRmAlarm = DailyRmAlarm
    }

    private fun getSwitchLanguage() {
        val languageLocal = getString(R.string.localization)
        if (languageLocal == "en-US") {
            rb_en.isChecked = true
            rb_id.isChecked = false
        } else if (languageLocal == "id-ID") {
            rb_en.isChecked = false
            rb_id.isChecked = true
        }
    }

    private fun setLanguage() {
        rb_set_language.setOnCheckedChangeListener { group: RadioGroup, checkedId: Int ->
            val rb = group.findViewById<RadioButton>(checkedId)
            if (rb != null) {
                when (checkedId) {
                    R.id.rb_en -> {
                        // do operations specific to this selection
                        locale = Locale("en")
                        rb.isChecked = true
                        setLocale()
                    }
                    R.id.rb_id -> {
                        // do operations specific to this selection
                        locale = Locale("in")
                        setLocale()
                    }
                    else -> {
                        locale = Locale("en")
                        setLocale()
                    }
                }
            }
        }
    }

    private fun setLocale() {
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale
        baseContext.resources.updateConfiguration(
            configuration,
            baseContext.resources.displayMetrics
        )
    }

    private fun getSwitchRm() {
        sw_dailiy_reminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                preference?.setDailyReminder(true)
                dailyRmAlarm.setRepeatingAlarm(
                    this,
                    getString(R.string.app_name),
                    getString(R.string.daily_message)
                )
            } else {
                preference?.setDailyReminder(false)
                dailyRmAlarm.cancelAlarm(this)
            }
        }
    }

    private fun btnSaveConfig() {
        btn_save_lang.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }
}

