package com.kajangdevs.netplay.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kajangdevs.netplay.DataDummy
import com.kajangdevs.netplay.data.Repository
import com.kajangdevs.netplay.data.source.local.entity.TvShowEntity
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

class TvShowViewModelTest {

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

    private fun createMockObserverTv(): Observer<Resource<List<TvShowEntity>>> = spyk(Observer { })

    @Test
    fun getTvShow() {
        val observer = createMockObserverTv()
        viewModel.tvShow.observeForever(observer)
        coEvery { repository.fetchTvShow() } returns DataDummy.testTvShow()

        viewModel.loadTvShow()

        verify { observer.onChanged(Resource.Loading) }
        verify { observer.onChanged(Resource.Success(DataDummy.testTvShow())) }
    }

    @Test
    fun getTvShowEmpty() {
        val observer = createMockObserverTv()
        viewModel.tvShow.observeForever(observer)
        coEvery { repository.fetchTvShow() } returns listOf()

        viewModel.loadTvShow()

        verify { observer.onChanged(Resource.Loading) }
        verify { observer.onChanged(Resource.Success(emptyList())) }
    }
}