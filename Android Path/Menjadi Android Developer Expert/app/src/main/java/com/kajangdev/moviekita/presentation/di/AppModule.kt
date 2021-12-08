package com.kajangdev.moviekita.presentation.di

import com.kajangdev.core.domain.usecase.Interactor
import com.kajangdev.core.domain.usecase.UseCase
import com.kajangdev.moviekita.presentation.detail.DetailViewModel
import com.kajangdev.moviekita.presentation.home.HomeViewModel
import com.kajangdev.moviekita.presentation.movie.MovieViewModel
import com.kajangdev.moviekita.presentation.tv.TvShowViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val useCaseModule = module {
    factory<UseCase> { Interactor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}