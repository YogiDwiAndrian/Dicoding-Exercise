package com.yogi.githubmedia.ui.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yogi.githubmedia.R

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
