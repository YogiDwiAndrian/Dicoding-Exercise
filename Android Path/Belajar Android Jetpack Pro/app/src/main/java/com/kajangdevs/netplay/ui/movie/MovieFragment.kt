package com.kajangdevs.netplay.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kajangdevs.netplay.R
import com.kajangdevs.netplay.data.source.local.entity.MovieEntity
import com.kajangdevs.netplay.utils.Resource
import com.kajangdevs.netplay.utils.gone
import com.kajangdevs.netplay.utils.visible
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_movie, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter()

        rv_movie.layoutManager = LinearLayoutManager(context)
        rv_movie.adapter = adapter

        movieViewModel.movie.observe(viewLifecycleOwner, ::updateUI)
        if (savedInstanceState == null) movieViewModel.load()

        sv_movie.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty()) {
                    movieViewModel.loadSearch(newText)
                } else {
                    movieViewModel.load()
                }
                return true
            }

        })
    }

    private fun updateUI(resource: Resource<List<MovieEntity>>) {
        when (resource) {
            is Resource.Loading -> {
                ld_movie.visible()
            }
            is Resource.Success -> {
                adapter.submitList(resource.data)
                ld_movie.gone()
            }
            is Resource.Failure -> {
            }
        }
    }

}