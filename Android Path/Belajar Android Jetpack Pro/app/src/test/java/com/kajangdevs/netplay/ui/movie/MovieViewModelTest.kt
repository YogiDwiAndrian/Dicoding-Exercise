package com.kajangdevs.netplay.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kajangdevs.netplay.DataDummy
import com.kajangdevs.netplay.data.Repository
import com.kajangdevs.netplay.data.source.local.entity.MovieEntity
import com.kajangdevs.netplay.ui.home.HomeViewModel
import com.kajangdevs.netplay.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var repository: Repository

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = HomeViewModel(repository)
    }

    private fun createMockObserverMv(): Observer<Resource<List<MovieEntity>>> = spyk(Observer { })

    @Test
    fun getMovie() {
        val observer = createMockObserverMv()
        viewModel.movie.observeForever(observer)
        coEvery { repository.fetchMovies() } returns DataDummy.testMovie()

        viewModel.loadMovie()

        verify { observer.onChanged(Resource.Loading) }
        verify { observer.onChanged(Resource.Success(DataDummy.testMovie())) }
    }

    @Test
    fun getMovieEmpty() {
        val observer = createMockObserverMv()
        viewModel.movie.observeForever(observer)
        coEvery { repository.fetchMovies() } returns listOf()

        viewModel.loadMovie()

        verify { observer.onChanged(Resource.Loading) }
        verify { observer.onChanged(Resource.Success(emptyList())) }
    }
}