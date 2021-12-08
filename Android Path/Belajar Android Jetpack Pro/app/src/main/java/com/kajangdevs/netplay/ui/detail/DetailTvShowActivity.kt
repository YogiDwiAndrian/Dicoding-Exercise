package com.kajangdevs.netplay.ui.detail

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kajangdevs.netplay.R
import com.kajangdevs.netplay.data.source.local.entity.TvShowDetailEntity
import com.kajangdevs.netplay.utils.Constant.getGenreNameTv
import com.kajangdevs.netplay.utils.Resource
import com.kajangdevs.netplay.utils.checkNull
import com.kajangdevs.netplay.utils.gone
import com.kajangdevs.netplay.utils.visible
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TVSHOWS = "extra_tvshows"
        const val EXTRA_ARRAY = "extra_array"
        const val BOOKMARK_TVSHOW = "bookmark_movies"
        const val EXTRA_GENRE = "extra_genre"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private var genre: String = ""
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        if (intent.getIntExtra(EXTRA_TVSHOWS, 0) != 0) {
            id = intent.getIntExtra(EXTRA_TVSHOWS, 0)
            genre = getGenreNameTv(intent.getIntegerArrayListExtra(EXTRA_ARRAY)!!)

        } else {
            id = intent.getIntExtra(BOOKMARK_TVSHOW, 0)
            genre = intent.getStringExtra(EXTRA_GENRE) ?: ""
        }

        detailViewModel.isBookmark.observe(this, ::isBookmark)
        detailViewModel.tvShow.observe(this, ::updateUI)
        detailViewModel.getTvShow(id, genre.checkNull())

        Log.d("gedebug1", id.toString())
    }

    private fun updateUI(resource: Resource<TvShowDetailEntity>) {
        when (resource) {
            is Resource.Loading -> {
                ld_detailTv.visible()
            }
            is Resource.Success -> {
                populateTvShow(resource.data)
                ld_detailTv.gone()
            }
            is Resource.Failure -> {
            }
        }
    }

    private fun populateTvShow(tvshowEntity: TvShowDetailEntity) {
        //binding item to ui
        Glide.with(this)
            .load(tvshowEntity.backdrop)
            .apply(
                RequestOptions.bitmapTransform(BlurTransformation(20, 3))
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(img_backdrop)

        Glide.with(this)
            .load(tvshowEntity.poster)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(img_poster)

        tv_tittle.text = tvshowEntity.title
        tv_release_year.text = tvshowEntity.released
        tv_status.text = tvshowEntity.status
        tv_season.text = tvshowEntity.season
        tv_episode.text = tvshowEntity.episode
        tv_rating.text = tvshowEntity.rating.toString()
        tv_count.text = tvshowEntity.voteCount
        tv_synopsis.text = tvshowEntity.synopsis
        tv_genre.text = genre.checkNull()
        tv_type.text = tvshowEntity.type
        tv_countries.text = tvshowEntity.countries
    }

    private fun isBookmark(isBookmark: Boolean) {
        if (isBookmark) {
            ic_bookmarkTv.setImageResource(R.drawable.ic_baseline_bookmark_24)
            ic_bookmarkTv.setOnClickListener {
                Toast.makeText(this, "Removed from bookmark", Toast.LENGTH_SHORT).show()
                detailViewModel.removeFromBookmarkTv()
            }
        } else {
            ic_bookmarkTv.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
            ic_bookmarkTv.setOnClickListener {
                Toast.makeText(this, "Added to bookmark", Toast.LENGTH_SHORT).show()
                detailViewModel.saveToBookmarkTv()
            }
        }
    }
}