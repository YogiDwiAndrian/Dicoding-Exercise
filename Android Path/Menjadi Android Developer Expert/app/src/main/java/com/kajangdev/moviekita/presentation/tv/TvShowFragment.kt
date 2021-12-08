package com.kajangdev.moviekita.presentation.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kajangdev.core.data.Resource
import com.kajangdev.core.domain.model.Movie
import com.kajangdev.core.ui.MovieAdapter
import com.kajangdev.core.utils.gone
import com.kajangdev.core.utils.visible
import com.kajangdev.moviekita.R
import com.kajangdev.moviekita.databinding.FragmentTvBinding
import com.kajangdev.moviekita.presentation.detail.DetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class TvShowFragment : Fragment() {

    private val tvShowViewModel: TvShowViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
                startActivity(intent)
            }

            getTv()
            getSearch()

            with(binding.rvTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            searchMovie()
        }
    }

    private fun getTv() {
        tvShowViewModel.tv.observe(viewLifecycleOwner, { tv ->
            if (tv != null) {
                tvObserver(tv)
            }
        })
    }

    private fun getSearch() {
        tvShowViewModel.tvResult.observe(viewLifecycleOwner, { tv ->
            if (tv != null) {
                tvObserver(tv)
            }
        })
    }

    private fun tvObserver(tv: Resource<List<Movie>>) {
        when (tv) {
            is Resource.Loading -> {
                binding.noData.gone()
                binding.progressShimmer.visible()
                movieAdapter.submitList(tv.data)
            }
            is Resource.Success -> {
                binding.noData.gone()
                binding.noConnection.gone()
                binding.progressShimmer.gone()
                movieAdapter.submitList(tv.data)
            }
            is Resource.Error -> {
                binding.progressShimmer.gone()
                binding.noData.visible()
                movieAdapter.submitList(tv.data)
            }
        }
    }

    private fun searchMovie() {
        binding.svTv.apply {
            queryHint = context.getString(R.string.search_tv)
            isIconified = false
            isFocusable = false
            isFocusableInTouchMode = true
            clearFocus()
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        tvShowViewModel.setSearchQuery(it)
                    }
                    if (newText.isNullOrEmpty()) getTv()
                    return true
                }

            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}