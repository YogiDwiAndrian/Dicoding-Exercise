package com.kajangdevs.netplay.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.espresso.idling.CountingIdlingResource
import com.kajangdevs.netplay.*
import com.kajangdevs.netplay.data.source.local.entity.MovieEntity
import com.kajangdevs.netplay.data.source.local.room.BookmarkDao
import com.kajangdevs.netplay.data.source.remote.networking.ApiService
import com.kajangdevs.netplay.utils.Resource
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class RepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var service: ApiService

    @MockK
    private lateinit var bookmarkDao: BookmarkDao

    @MockK
    lateinit var idlingResource: CountingIdlingResource

    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = Repository(service, bookmarkDao, idlingResource)
        every { idlingResource.increment() }.answers { }
        every { idlingResource.decrement() }.answers { }
    }

    @Test
    fun fetchMovies() {
        val dummyData = DataDummy.movieResponse()
        coEvery { service.getMovies() }
            .returns(dummyData)

        runBlocking {
            val result = repository.fetchMovies()
            assert(result.size == 1)
            assert(result.first().title == dummyData.results.first().title)
        }
    }

    @Test
    fun fetchTvShow() {
        val dataDummy = DataDummy.tvShowResponse()
        coEvery { service.getTvShow() }
            .returns(dataDummy)

        runBlocking {
            val result = repository.fetchTvShow()
            assert(result.size == 1)
            assert(result.first().tvShowId == dataDummy.results.first().id)
        }
    }

    @Test
    fun fetchMoviesSearch() {
        val dataDummy = DataDummy.movieResponse()
        coEvery { service.getMoviesSearch("a") }
            .returns(dataDummy)

        runBlocking {
            val result = repository.fetchMoviesSearch("a")
            assert(result.size == 1)
            assert(result.first().title == dataDummy.results.first().title)
        }
    }

    @Test
    fun fetchTvShowSearch() {
        val dataDummy = DataDummy.tvShowResponse()
        coEvery { service.getTvShowSearch("a") }
            .returns(dataDummy)

        runBlocking {
            val result = repository.fetchTvShowSearch("a")
            assert(result.size == 1)
            assert(result.first().tvShowId == dataDummy.results.first().id)
        }
    }

    @Test
    fun fetchMovieDetailById() {
        val movieId = 1
        val dataDummy = DataDummy.movieDetailResponse()
        coEvery { service.getMovieDetail(any()) }
            .returns(dataDummy)

        runBlocking {
            val result = repository.fetchMovieDetailById(movieId)
            assert(result.title == dataDummy.title)
        }
    }

    @Test
    fun fetchTvShowDetailById() {
        val movieId = 1
        val dataDummy = DataDummy.tvDetailResponse()
        coEvery { service.getTvShowDetail(any()) }
            .returns(dataDummy)

        runBlocking {
            val result = repository.fetchTvShowDetailById(movieId)
            assert(result.title == dataDummy.name)
        }
    }

    @Test
    fun getBookmarkMovies() {
        val dataSource = DataDummy.movieBookmark().asMockDataSourceFactory()
        every { bookmarkDao.getBookmarkMovies() }.returns(dataSource)

        repository.getBookmarkMovies().testObserver()

        val expected = DataDummy.movieBookmark().asPagedList()
        verify { bookmarkDao.getBookmarkMovies() }
        verify { repository.getBookmarkMovies().hasObservers() }
        assert(repository.getBookmarkMovies().getOrAwaitValue().size == expected.size)
    }

    @Test
    fun getBookmarkTvShow() {
        val dataSource = DataDummy.tvBookmark().asMockDataSourceFactory()
        every { bookmarkDao.getBookmarkTvShow() }.returns(dataSource)

        repository.getBookmarkTvShow().testObserver()

        val expected = DataDummy.movieBookmark().asPagedList()
        verify { bookmarkDao.getBookmarkTvShow() }
        verify { repository.getBookmarkTvShow().hasObservers() }
        assert(repository.getBookmarkTvShow().getOrAwaitValue().size == expected.size)
    }

    @Test
    fun getBookmarkMovieById() {
        val id = 1
        coEvery { bookmarkDao.getBookmarkMovieById(any()) }.returns(DataDummy.movieBookmark().first())

        runBlocking {
            val result = repository.getBookmarkMovieById(id)
            val expected = DataDummy.movieBookmark().first()
            verify { bookmarkDao.getBookmarkMovieById(any()) }
            assert(result != null)
            assert(result?.title == expected.title)
        }
    }

    @Test
    fun getBookmarkTvShowById() {
        val id = 1
        coEvery { bookmarkDao.getBookmarkTvShowById(any()) }.returns(DataDummy.tvBookmark().first())

        runBlocking {
            val result = repository.getBookmarkTvShowById(id)
            val expected = DataDummy.tvBookmark().first()
            verify { bookmarkDao.getBookmarkTvShowById(any()) }
            assert(result != null)
            assert(result?.title == expected.title)
        }
    }

    @Test
    fun addBookmarkMovie() {
        val item = DataDummy.movieBookmark().first()
        coEvery { bookmarkDao.insertMv(any()) }.answers { println("succesfully to bookmark movie") }

        runBlocking {
            repository.addBookmarkMovie(item)
            coVerify { bookmarkDao.insertMv(any()) }
        }
    }

    @Test
    fun addBookmarkTvShow() {
        val item = DataDummy.tvBookmark().first()
        coEvery { bookmarkDao.insertTv(any()) }.answers { println("succesfully to bookmark tv show") }

        runBlocking {
            repository.addBookmarkTvShow(item)
            coVerify {  bookmarkDao.insertTv(any()) }
        }
    }

    @Test
    fun rmBookmarkMovie() {
        val id = 1
        coEvery { bookmarkDao.deleteMv(any()) }.answers { println("id:${args.first()} removed from db") }

        runBlocking {
            repository.rmBookmarkMovie(id)
            coVerify { bookmarkDao.deleteMv(any()) }
        }
    }

    @Test
    fun rmBookmarkTvShow() {
        val id = 1
        coEvery { bookmarkDao.deleteTv(any()) }.answers { println("id:${args.first()} removed from db") }

        runBlocking {
            repository.rmBookmarkTvShow(id)
            coVerify { bookmarkDao.deleteTv(any()) }
        }
    }
}