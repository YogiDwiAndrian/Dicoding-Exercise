package com.kajangdev.moviekita.bookmark.di

import com.kajangdev.moviekita.bookmark.BookmarkViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarkModule = module {
    viewModel { BookmarkViewModel(get()) }
}