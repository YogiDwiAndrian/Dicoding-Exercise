package com.kajangdevs.netplay.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kajangdevs.netplay.DataDummy
import com.kajangdevs.netplay.data.Repository
import com.kajangdevs.netplay.data.source.local.entity.MovieDetailEntity
import com.kajangdevs.netplay.data.source.local.entity.TvShowDetailEntity
import com.kajangdevs.netplay.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var repository: Repository

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = DetailViewModel(repository)
        coEvery { repository.getBookmarkMovieById(any()) }
            .returns(null)
        coEvery { repository.getBookmarkTvShowById(any()) }
            .returns(null)
    }

    private fun createMockObserverMv(): Observer<Resource<MovieDetailEntity>> = spyk(Observer { })
    private fun createMockObserverTv(): Observer<Resource<TvShowDetailEntity>> = spyk(Observer { })

    @Test
    fun getMovie() {
        val movieId = 0
        val observer = createMockObserverMv()
        val dataDummy = DataDummy.testDetailMovie().first()
        coEvery { repository.fetchMovieDetailById(any()) }
            .returns(dataDummy)

        viewModel.movie.observeForever(observer)
        viewModel.getMovie(movieId, "genre")

        verify { observer.onChanged(Resource.Loading) }
        verify { observer.onChanged(Resource.Success(dataDummy)) }
    }

    @Test
    fun getTvShow() {
        val tvShowId = 0
        val observer = createMockObserverTv()
        val dataDummy = DataDummy.testDetailTvSHow().first()
        coEvery { repository.fetchTvShowDetailById(any()) }
            .returns(dataDummy)

        viewModel.tvShow.observeForever(observer)
        viewModel.getTvShow(tvShowId, "action")

        verify { observer.onChanged(Resource.Loading) }
        verify { observer.onChanged(Resource.Success(dataDummy)) }
    }
}