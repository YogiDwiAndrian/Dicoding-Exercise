package com.kajangdev.moviekita.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kajangdev.moviekita.R
import com.kajangdev.moviekita.presentation.MainActivity

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