package com.kajangdevs.netplay.ui.detail

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kajangdevs.netplay.R
import com.kajangdevs.netplay.data.source.local.entity.MovieDetailEntity
import com.kajangdevs.netplay.utils.Constant.getGenreNameMv
import com.kajangdevs.netplay.utils.Resource
import com.kajangdevs.netplay.utils.checkNull
import com.kajangdevs.netplay.utils.gone
import com.kajangdevs.netplay.utils.visible
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_detail_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
        const val EXTRA_ARRAY = "extra_array"
        const val BOOKMARK_MOVIES = "bookmark_movies"
        const val EXTRA_GENRE = "extra_genre"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private var genre: String = ""
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        if (intent.getIntExtra(EXTRA_MOVIES, 0) != 0) {
            id = intent.getIntExtra(EXTRA_MOVIES, 0)
            genre = getGenreNameMv(intent.getIntegerArrayListExtra(EXTRA_ARRAY)!!)

        } else {
            id = intent.getIntExtra(BOOKMARK_MOVIES, 0)
            genre = intent.getStringExtra(EXTRA_GENRE) ?: ""
        }

        detailViewModel.isBookmark.observe(this, ::isBookmark)
        detailViewModel.movie.observe(this, ::updateUI)
        detailViewModel.getMovie(id, genre.checkNull())
        Log.d("gedebug2", id.toString())
    }

    private fun updateUI(resource: Resource<MovieDetailEntity>) {
        when (resource) {
            is Resource.Loading -> {
                ld_detailMv.visible()
            }
            is Resource.Success -> {
                populateMovie(resource.data)
                ld_detailMv.gone()
            }
            is Resource.Failure -> {
            }
        }
    }

    private fun populateMovie(movieEntity: MovieDetailEntity) {
        //binding item to ui
        Glide.with(this)
            .load(movieEntity.backdrop)
            .apply(
                RequestOptions.bitmapTransform(BlurTransformation(20, 3))
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(img_backdrop)


        Glide.with(this)
            .load(movieEntity.poster)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(img_poster)

        tv_tittle.text = movieEntity.title
        tv_release_year.text = movieEntity.released
        tv_runtime.text = movieEntity.runtime
        tv_revenue.text = movieEntity.revenue
        tv_rating.text = movieEntity.rating.toString()
        tv_count.text = movieEntity.voteCount
        tv_synopsis.text = movieEntity.synopsis
        tv_budget.text = movieEntity.budget
        tv_genre.text = genre.checkNull()
        tv_countries.text = movieEntity.production.checkNull()
    }

    private fun isBookmark(isBookmark: Boolean) {
        if (isBookmark) {
            Glide.with(this)
                .load(R.drawable.ic_baseline_bookmark_24)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(ic_bookmarkMv)
            ic_bookmarkMv.setOnClickListener {
                Toast.makeText(this, "Removed from bookmark", Toast.LENGTH_SHORT).show()
                detailViewModel.removeFromBookmarkMv()
            }
        } else {
            Glide.with(this)
                .load(R.drawable.ic_baseline_bookmark_border_24)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(ic_bookmarkMv)
            ic_bookmarkMv.setOnClickListener {
                Toast.makeText(this, "Added to bookmark", Toast.LENGTH_SHORT).show()
                detailViewModel.saveToBookmarkMv()
            }
        }
    }
}