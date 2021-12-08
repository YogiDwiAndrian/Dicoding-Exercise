package com.kajangdevs.netplay.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kajangdevs.netplay.R
import com.kajangdevs.netplay.ui.bookmark.adapter.BookmarkTvShowAdapter
import com.kajangdevs.netplay.ui.bookmark.viewmodel.BookmarkViewModel
import kotlinx.android.synthetic.main.fragment_bookmark_tvshow.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowBmFragment : Fragment() {

    private val tvBmViewModel: BookmarkViewModel by viewModel()
    private lateinit var adapter: BookmarkTvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BookmarkTvShowAdapter()

        rv_tvShowBm.adapter = adapter
        rv_tvShowBm.layoutManager = LinearLayoutManager(context)

        tvBmViewModel.tvShowPaged.observe(viewLifecycleOwner, {
            isEmptyView(it.isEmpty())
            adapter.submitList(it)
        })
    }

    private fun isEmptyView(toggle: Boolean) {
        rv_tvShowBm.visibility = if (toggle) View.GONE else View.VISIBLE
        tv_emptyBmTvShow.visibility = if (toggle) View.VISIBLE else View.GONE
    }

}