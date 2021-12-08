package com.yogi.githubmedia.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.yogi.githubmedia.R
import com.yogi.githubmedia.data.networking.Responses
import com.yogi.githubmedia.models.Github
import com.yogi.githubmedia.ui.menu.AboutActivity
import com.yogi.githubmedia.ui.menu.FavoriteActivity
import com.yogi.githubmedia.ui.menu.SettingActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var rvGithub: RecyclerView
    private lateinit var pBar: ProgressBar
    private var list: ArrayList<Github> = arrayListOf()
    private var twiceExit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pBar = menu_load

        AndroidNetworking.initialize(this)

        rvGithub = rv_github_main
        rvGithub.setHasFixedSize(true)

        rvGithub.layoutManager = LinearLayoutManager(this)
        Responses().getGithubUsers(list, rvGithub, pBar)

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    list.clear()
                    Responses().searchGithubUsers(list, rvGithub, newText, this@MainActivity, pBar)
                } else {
                    Responses().getGithubUsers(list, rvGithub, pBar)
                }
                return true
            }

        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_about -> {
            val moveIntent = Intent(this, AboutActivity::class.java)
            startActivity(moveIntent)
            true
        }
        R.id.action_setting -> {
            val moveIntent = Intent(this, SettingActivity::class.java)
            startActivity(moveIntent)
            true
        }
        R.id.action_favorite -> {
            val moveIntent = Intent(this, FavoriteActivity::class.java)
            startActivity(moveIntent)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
    }

    public override fun onPause() {
        super.onPause()
    }

    public override fun onResume() {
        super.onResume()
    }

    public override fun onRestart() {
        super.onRestart()
    }

    override fun onBackPressed() {
        if (twiceExit) {
            super.onBackPressed()
            return
        }

        this.twiceExit = true
        Toast.makeText(this, R.string.tap_exit, Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ twiceExit = false }, 2000)
    }

}
