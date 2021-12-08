package com.kajangdev.moviekita.presentation.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kajangdev.core.data.Resource
import com.kajangdev.core.domain.model.Movie
import com.kajangdev.core.domain.model.Slides
import com.kajangdev.core.ui.HorizontalMvAdapter
import com.kajangdev.core.ui.HorizontalTvAdapter
import com.kajangdev.core.ui.SliderAdapter
import com.kajangdev.core.utils.gone
import com.kajangdev.core.utils.invisible
import com.kajangdev.core.utils.visible
import com.kajangdev.moviekita.databinding.FragmentHomeBinding
import com.kajangdev.moviekita.presentation.detail.DetailActivity
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var horizontalMvAdapter: HorizontalMvAdapter? = HorizontalMvAdapter()
    private var horizontalTvAdapter: HorizontalTvAdapter? = HorizontalTvAdapter()
    private var sliderAdapter: SliderAdapter? = SliderAdapter()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            horizontalMvAdapter!!.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
                startActivity(intent)
            }

            horizontalTvAdapter!!.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
                startActivity(intent)
            }

            getTv()
            getMovie()
            getSlides()
        }
    }

    private fun getMovie() {
        homeViewModel.movie.observe(viewLifecycleOwner, ::updateUiMv)

        with(binding.rvPopMovie) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = horizontalMvAdapter
        }
    }

    private fun getTv() {
        homeViewModel.tv.observe(viewLifecycleOwner, ::updateUiTv)

        with(binding.rvPopTv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = horizontalTvAdapter
        }
    }

    private fun getSlides() {
        homeViewModel.slide.observe(viewLifecycleOwner, ::updateUiSlide)
    }

    private fun updateUiMv(resource: Resource<List<Movie>>) {
        when (resource) {
            is Resource.Loading -> binding.progressShimmerMv.visible()
            is Resource.Success -> {
                binding.progressShimmerMv.invisible()
                horizontalMvAdapter!!.submitList(resource.data)
            }
            is Resource.Error -> {
                binding.progressShimmerMv.invisible()
                binding.noConnectionMv.visible()
            }
        }
    }

    private fun updateUiTv(resource: Resource<List<Movie>>) {
        when (resource) {
            is Resource.Loading -> binding.progressShimmerTv.visible()
            is Resource.Success -> {
                binding.progressShimmerTv.gone()
                horizontalTvAdapter!!.submitList(resource.data)
            }
            is Resource.Error -> {
                binding.progressShimmerTv.gone()
                binding.noConnectionTv.visible()
            }
        }
    }

    private fun updateUiSlide(resource: Resource<List<Slides>>) {
        when (resource) {
            is Resource.Loading -> binding.progressShimmerSlider.visible()
            is Resource.Success -> {
                binding.progressShimmerSlider.gone()

                sliderAdapter!!.setSlides(resource.data)
                binding.imageSlider.setSliderAdapter(sliderAdapter!!)

                with(binding.imageSlider){
                    setIndicatorAnimation(IndicatorAnimationType.DROP)
                    setSliderTransformAnimation(SliderAnimations.CUBEOUTROTATIONTRANSFORMATION)
                    autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
                    indicatorSelectedColor = Color.WHITE
                    indicatorUnselectedColor = Color.GRAY
                    scrollTimeInSec = 4 //set scroll delay in seconds :

                    startAutoCycle()
                }
            }
            is Resource.Error -> {
                binding.progressShimmerSlider.gone()
                binding.noConnectionSlide.visible()
            }
        }
    }

    override fun onDestroyView() {
        binding.imageSlider.stopAutoCycle()
        super.onDestroyView()
        sliderAdapter = null
        horizontalMvAdapter = null
        horizontalTvAdapter = null
        _binding = null
    }
}