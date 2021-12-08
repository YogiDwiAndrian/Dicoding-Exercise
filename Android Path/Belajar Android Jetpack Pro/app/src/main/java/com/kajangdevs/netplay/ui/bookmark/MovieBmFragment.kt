package com.kajangdevs.netplay.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kajangdevs.netplay.R
import com.kajangdevs.netplay.ui.bookmark.adapter.BookmarkMovieAdapter
import com.kajangdevs.netplay.ui.bookmark.viewmodel.BookmarkViewModel
import kotlinx.android.synthetic.main.fragment_bookmark_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieBmFragment : Fragment() {

    private val movieBmViewModel: BookmarkViewModel by viewModel()
    private lateinit var adapter: BookmarkMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BookmarkMovieAdapter()

        rv_movieBm.adapter = adapter
        rv_movieBm.layoutManager = LinearLayoutManager(context)

        movieBmViewModel.moviePaged.observe(viewLifecycleOwner, {
            isEmptyView(it.isEmpty())
            adapter.submitList(it)
        })
    }

    private fun isEmptyView(toggle: Boolean) {
        rv_movieBm.visibility = if (toggle) View.GONE else View.VISIBLE
        tv_emptyBmMovie.visibility = if (toggle) View.VISIBLE else View.GONE
    }
}