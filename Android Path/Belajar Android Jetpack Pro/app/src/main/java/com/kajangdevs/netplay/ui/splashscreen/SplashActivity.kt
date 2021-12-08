package com.kajangdevs.netplay.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kajangdevs.netplay.R
import com.kajangdevs.netplay.ui.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(mainLooper).postDelayed({

            startActivity(Intent(this, MainActivity::class.java))

            finish()
        }, 3500)
    }
}