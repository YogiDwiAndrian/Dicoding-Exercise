package com.kajangdev.moviekita.presentation.movie

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
import com.kajangdev.moviekita.databinding.FragmentMovieBinding
import com.kajangdev.moviekita.presentation.detail.DetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
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

            getMovie()
            getSearch()

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            searchMovie()
        }
    }

    private fun getMovie() {
        movieViewModel.movie.observe(viewLifecycleOwner, { movie ->
            if (movie != null) {
                movieObserver(movie)
            }
        })
    }

    private fun getSearch() {
        movieViewModel.movieResult.observe(viewLifecycleOwner, { movie ->
            if (movie != null) {
                movieObserver(movie)
            }
        })
    }

    private fun movieObserver(movie: Resource<List<Movie>>) {
        when (movie) {
            is Resource.Loading -> {
                binding.noData.gone()
                binding.progressShimmer.visible()
                movieAdapter.submitList(movie.data)
            }
            is Resource.Success -> {
                binding.noData.gone()
                binding.noConnection.gone()
                binding.progressShimmer.gone()
                movieAdapter.submitList(movie.data)
            }
            is Resource.Error -> {
                binding.progressShimmer.gone()
                binding.noData.visible()
                movieAdapter.submitList(movie.data)
            }
        }
    }

    private fun searchMovie() {
        binding.svMovie.apply {
            queryHint = context.getString(R.string.search_movie)
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
                        movieViewModel.setSearchQuery(it)
                    }
                    if (newText.isNullOrEmpty()) getMovie()
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