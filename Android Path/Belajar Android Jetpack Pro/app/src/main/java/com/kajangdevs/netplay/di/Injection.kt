package com.kajangdevs.netplay.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kajangdevs.netplay.data.DataSource
import com.kajangdevs.netplay.data.Repository
import com.kajangdevs.netplay.data.source.local.room.NetPlayDatabase
import com.kajangdevs.netplay.data.source.remote.networking.ApiService
import com.kajangdevs.netplay.ui.bookmark.viewmodel.BookmarkViewModel
import com.kajangdevs.netplay.ui.detail.DetailViewModel
import com.kajangdevs.netplay.ui.home.HomeViewModel
import com.kajangdevs.netplay.ui.movie.MovieViewModel
import com.kajangdevs.netplay.ui.tvshow.TvShowViewModel
import com.kajangdevs.netplay.utils.Constant.API_KEY
import com.kajangdevs.netplay.utils.Constant.BASE_URL
import com.kajangdevs.netplay.utils.EspressoIdlingResource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkInjection = module {
    single<Gson> { GsonBuilder().create() }
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor {
                val original = it.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()
                val requestBuilder = original.newBuilder().url(url)
                val request = requestBuilder.build()
                it.proceed(request)
            }
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(ApiService::class.java) as ApiService
    }
}

val applicationInjection = module {
    single { NetPlayDatabase.getInstance(get()).bookmarkDao() }
    single {
        Repository(
            get(),
            get(),
            EspressoIdlingResource.getEspressoIdlingResource()
        ) as DataSource
    }
}

val viewModelInjection = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { BookmarkViewModel(get()) }
}