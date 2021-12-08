package com.kajangdev.moviekita.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kajangdev.core.domain.model.Movie
import com.kajangdev.core.ui.MovieAdapter
import com.kajangdev.core.utils.visible
import com.kajangdev.moviekita.bookmark.databinding.FragmentBookmarkTabsBinding
import com.kajangdev.moviekita.presentation.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class BookmarkTabsFragment(private val isTvShow: Boolean) : Fragment() {

    private val bookmarkViewModel: BookmarkViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    private var _binding: FragmentBookmarkTabsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkTabsBinding.inflate(inflater, container, false)
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

            setContent()

            with(binding.rvMovieBm) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun setContent() {
        if (isTvShow) {
            bookmarkViewModel.getBookmarkTV.observe(viewLifecycleOwner, moviesObserver)
        } else {
            bookmarkViewModel.getBookmarkMovie.observe(viewLifecycleOwner, moviesObserver)
        }
    }

    private val moviesObserver = Observer<List<Movie>> { movies ->

        if (movies.isNullOrEmpty()) binding.noData.visible()

        movieAdapter.submitList(movies)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}