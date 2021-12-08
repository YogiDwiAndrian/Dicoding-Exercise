package com.kajangdevs.netplay.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kajangdevs.netplay.R
import com.kajangdevs.netplay.data.source.local.entity.TvShowEntity
import com.kajangdevs.netplay.utils.Resource
import com.kajangdevs.netplay.utils.gone
import com.kajangdevs.netplay.utils.visible
import kotlinx.android.synthetic.main.fragment_tvshow.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private val tvShowViewModel: TvShowViewModel by viewModel()
    private lateinit var adapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_tvshow, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TvShowAdapter()

        rv_tv_show.layoutManager = LinearLayoutManager(context)
        rv_tv_show.adapter = adapter

        tvShowViewModel.tvShow.observe(viewLifecycleOwner, ::updateUI)
        if (savedInstanceState == null) tvShowViewModel.load()

        sv_tvShow.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty()) {
                    tvShowViewModel.loadSearch(newText)
                } else {
                    tvShowViewModel.load()
                }
                return true
            }

        })
    }

    private fun updateUI(resource: Resource<List<TvShowEntity>>) {
        when (resource) {
            is Resource.Loading -> {
                ld_tvShow.visible()
            }
            is Resource.Success -> {
                adapter.submitList(resource.data)
                ld_tvShow.gone()
            }
            is Resource.Failure -> {
                ld_tvShow.gone()
            }
        }
    }

}