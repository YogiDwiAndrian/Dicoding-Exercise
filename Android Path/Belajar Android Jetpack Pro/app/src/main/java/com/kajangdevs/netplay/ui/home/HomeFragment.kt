package com.kajangdevs.netplay.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kajangdevs.netplay.R
import com.kajangdevs.netplay.data.source.local.entity.MovieEntity
import com.kajangdevs.netplay.data.source.local.entity.TvShowEntity
import com.kajangdevs.netplay.utils.Resource
import com.kajangdevs.netplay.utils.gone
import com.kajangdevs.netplay.utils.visible
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var movieAdapter: MovieHorizontalAdapter
    private lateinit var tvShowAdapter: TvShowHorizontalAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        hastagRV()
        movieRV()
        tvshowRV()
        slideShow()
    }

    private fun slideShow() {
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DummyViewModel::class.java]
        val slide = viewModel.getSlide()

        val movieAdapter = SliderAdapter()
        movieAdapter.setSlides(slide)

        imageSlider.setSliderAdapter(movieAdapter)

        imageSlider.setIndicatorAnimation(IndicatorAnimationType.DROP)
        imageSlider.setSliderTransformAnimation(SliderAnimations.CUBEOUTROTATIONTRANSFORMATION)
        imageSlider.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        imageSlider.indicatorSelectedColor = Color.WHITE
        imageSlider.indicatorUnselectedColor = Color.GRAY
        imageSlider.scrollTimeInSec = 4 //set scroll delay in seconds :

        imageSlider.startAutoCycle()
    }


    private fun hastagRV() {
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DummyViewModel::class.java]
        val hastag = viewModel.getHastag()

        val hastagAdapter = HastagAdapter()
        hastagAdapter.setMovies(hastag)

        with(rv_hastag) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = hastagAdapter
        }
    }

    private fun movieRV() {
        movieAdapter = MovieHorizontalAdapter()

        rv_popular_movie.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_popular_movie.adapter = movieAdapter

        viewModel.movie.observe(viewLifecycleOwner, ::updateUiMovie)
        viewModel.loadMovie()
    }

    private fun tvshowRV() {
        tvShowAdapter = TvShowHorizontalAdapter()

        rv_popular_tvshow.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_popular_tvshow.adapter = tvShowAdapter

        viewModel.tvShow.observe(viewLifecycleOwner, ::updateUiTv)
        viewModel.loadTvShow()
    }

    private fun updateUiMovie(resource: Resource<List<MovieEntity>>) {
        when (resource) {
            is Resource.Loading -> {
                ld_horizonMv.visible()
            }
            is Resource.Success -> {
                movieAdapter.submitList(resource.data)
                ld_horizonMv.gone()
            }
            is Resource.Failure -> {
            }
        }
    }

    private fun updateUiTv(resource: Resource<List<TvShowEntity>>) {
        when (resource) {
            is Resource.Loading -> {
                ld_horizonTv.visible()
            }
            is Resource.Success -> {
                tvShowAdapter.submitList(resource.data)
                ld_horizonTv.gone()
            }
            is Resource.Failure -> {

            }
        }
    }
}