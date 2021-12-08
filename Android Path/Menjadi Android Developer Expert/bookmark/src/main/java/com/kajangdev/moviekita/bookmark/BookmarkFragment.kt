package com.kajangdev.moviekita.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kajangdev.moviekita.bookmark.databinding.FragmentBookmarkBinding
import com.kajangdev.moviekita.bookmark.di.bookmarkModule
import org.koin.core.context.loadKoinModules


class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(bookmarkModule)

        sectionsPagerAdapter = SectionsPagerAdapter(this, childFragmentManager)
        binding.pager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.pager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}