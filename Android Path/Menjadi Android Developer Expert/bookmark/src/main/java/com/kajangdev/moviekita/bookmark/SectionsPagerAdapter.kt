package com.kajangdev.moviekita.bookmark

import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kajangdev.moviekita.R

class SectionsPagerAdapter(private val mContext: BookmarkFragment, fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val tabTittles = intArrayOf(R.string.title_movies, R.string.title_tv)

    override fun getCount(): Int {
        return 2
    }

    private val fragment: List<Fragment> = listOf(
            BookmarkTabsFragment(isTvShow = false),
            BookmarkTabsFragment(isTvShow = true)
    )

    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence {
        return mContext.resources.getString(tabTittles[position])
    }
}