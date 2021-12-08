package com.kajangdev.moviekita.presentation.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kajangdev.core.domain.model.Movie
import com.kajangdev.core.utils.checkNull
import com.kajangdev.moviekita.R
import com.kajangdev.moviekita.databinding.ActivityDetailBinding
import jp.wasabeef.glide.transformations.BlurTransformation
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    private var isBookmark = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detail = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        showDetail(detail)

        checkIsBookmarked(detail?.id ?: 0)

        binding.ibBack.setOnClickListener { onBackPressed() }
        binding.icShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, getString(R.string.share))
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    private fun showDetail(detail: Movie?) {
        detail?.let {
            with(binding){
                Glide.with(baseContext)
                        .load(detail.backdrop)
                        .apply(RequestOptions.bitmapTransform(BlurTransformation(20, 3))
                                .placeholder(R.drawable.loading_backdrop)
                                .error(R.drawable.error_backdrop)
                        )
                        .into(ivBackdrop)

                Glide.with(baseContext)
                        .load(detail.poster)
                        .apply(RequestOptions.placeholderOf(R.drawable.loading_poster)
                                .error(R.drawable.error_poster)
                        )
                        .into(ivPoster)

                tvTitle.text = detail.title
                tvReleaseMv.text = detail.released
                tvOverview.text = detail.overview
                tvRating.text = detail.rating.toString()
                tvCount.text = detail.voteCount
                tvGenreMv.text = detail.genre ?: "".checkNull()
                tvLanguage.text = detail.language

                icBookmark.setOnClickListener {
                    isBookmark = if (isBookmark) {
                        detailViewModel.deleteItem(detail.id ?: 0)
                        icBookmark.setImageDrawable(ContextCompat.getDrawable(baseContext, R.drawable.ic_baseline_bookmark_border_24))
                        false
                    } else {
                        detailViewModel.insertItem(detail)
                        icBookmark.setImageDrawable(ContextCompat.getDrawable(baseContext, R.drawable.ic_baseline_bookmark_24))
                        true
                    }
                }
            }

        }
    }

    private fun checkIsBookmarked(id: Int) {
        detailViewModel.checkBookmark(id).observe(this, { detail ->
            isBookmark = if (detail.id != null) {
                binding.icBookmark.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_bookmark_24))
                true
            } else {
                binding.icBookmark.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_bookmark_border_24))
                false
            }
        })

    }
}