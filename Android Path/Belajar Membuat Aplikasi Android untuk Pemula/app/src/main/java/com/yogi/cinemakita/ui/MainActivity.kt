package com.yogi.cinemakita.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogi.cinemakita.R
import com.yogi.cinemakita.adapters.MovieRowAdapter
import com.yogi.cinemakita.data.MovieData
import com.yogi.cinemakita.models.Movie
import com.yogi.cinemakita.ui.menu.AboutActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var rvMovie: RecyclerView
    private var list: ArrayList<Movie> = arrayListOf()
    private var twiceExit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMovie = rv_movie_row
        rvMovie.setHasFixedSize(true)

        list.addAll(MovieData.listData)
        showRecyclerCardView()

    }

    //setting menu in action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_about -> {
            val moveIntent = Intent(this, AboutActivity::class.java)
            startActivity(moveIntent)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
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
        Toast.makeText(this, "Tap again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ twiceExit = false }, 2000)
    }

    private fun showRecyclerCardView() {
        rvMovie.layoutManager = LinearLayoutManager(this)
        rvMovie.adapter = MovieRowAdapter(list)
    }
}
