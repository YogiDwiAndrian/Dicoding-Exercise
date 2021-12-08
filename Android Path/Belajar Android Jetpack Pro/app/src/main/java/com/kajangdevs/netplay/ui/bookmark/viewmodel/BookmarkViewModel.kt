package com.kajangdevs.netplay.ui.bookmark.viewmodel

import androidx.lifecycle.ViewModel
import com.kajangdevs.netplay.data.DataSource

class BookmarkViewModel(dataSource: DataSource) : ViewModel() {

    val moviePaged = dataSource.getBookmarkMovies()
    val tvShowPaged = dataSource.getBookmarkTvShow()

}