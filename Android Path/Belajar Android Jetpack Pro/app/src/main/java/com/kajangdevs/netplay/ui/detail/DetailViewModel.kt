package com.kajangdevs.netplay.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kajangdevs.netplay.data.DataSource
import com.kajangdevs.netplay.data.source.local.entity.MovieDetailEntity
import com.kajangdevs.netplay.data.source.local.entity.TvShowDetailEntity
import com.kajangdevs.netplay.data.source.local.entity.toBookmarkMv
import com.kajangdevs.netplay.data.source.local.entity.toBookmarkTv
import com.kajangdevs.netplay.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val dataSource: DataSource) : ViewModel() {

    private var movieBm: MovieDetailEntity? = null
    private var tvBm: TvShowDetailEntity? = null
    private var _movie = MutableLiveData<Resource<MovieDetailEntity>>()
    val movie: LiveData<Resource<MovieDetailEntity>> = _movie
    private var _tvShow = MutableLiveData<Resource<TvShowDetailEntity>>()
    val tvShow: LiveData<Resource<TvShowDetailEntity>> = _tvShow
    private var _isBookmark = MutableLiveData(false)
    val isBookmark = _isBookmark as LiveData<Boolean>
    private var genre: String = ""


    fun getTvShow(id: Int, genre: String) {
        _tvShow.postValue(Resource.Loading)
        this.genre = genre
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val responsResult = dataSource.fetchTvShowDetailById(id)
                tvBm = responsResult
                _isBookmark.postValue(checkIsBookmarkedTv(responsResult.tvShowId))
                _tvShow.postValue(Resource.Success(responsResult))
            } catch (t: Throwable) {
                _tvShow.postValue(Resource.Failure(t))
            }
        }
    }

    fun getMovie(id: Int, genre: String) {
        _movie.postValue(Resource.Loading)
        this.genre = genre
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val responsResult = dataSource.fetchMovieDetailById(id)
                movieBm = responsResult
                _isBookmark.postValue(checkIsBookmarkedMv(responsResult.movieId))
                _movie.postValue(Resource.Success(responsResult))
            } catch (t: Throwable) {
                _movie.postValue(Resource.Failure(t))
            }
        }
    }

    private suspend fun checkIsBookmarkedMv(id: Int): Boolean {
        return dataSource.getBookmarkMovieById(id) != null
    }

    private suspend fun checkIsBookmarkedTv(id: Int): Boolean {
        return dataSource.getBookmarkTvShowById(id) != null
    }

    fun saveToBookmarkMv() {
        movieBm?.let {
            viewModelScope.launch {
                dataSource.addBookmarkMovie(it.toBookmarkMv(genre))
                _isBookmark.value = true
            }
        }
    }

    fun saveToBookmarkTv() {
        tvBm?.let {
            viewModelScope.launch {
                dataSource.addBookmarkTvShow(it.toBookmarkTv(genre))
                _isBookmark.value = true
            }
        }
    }

    fun removeFromBookmarkMv() {
        movieBm?.let {
            viewModelScope.launch {
                dataSource.rmBookmarkMovie(it.movieId)
                _isBookmark.value = false
            }
        }
    }

    fun removeFromBookmarkTv() {
        tvBm?.let {
            viewModelScope.launch {
                dataSource.rmBookmarkTvShow(it.tvShowId)
                _isBookmark.value = false
            }
        }
    }
}