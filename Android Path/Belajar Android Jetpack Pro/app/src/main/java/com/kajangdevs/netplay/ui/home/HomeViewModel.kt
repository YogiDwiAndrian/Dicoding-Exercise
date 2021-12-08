package com.kajangdevs.netplay.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kajangdevs.netplay.data.DataSource
import com.kajangdevs.netplay.data.source.local.entity.MovieEntity
import com.kajangdevs.netplay.data.source.local.entity.TvShowEntity
import com.kajangdevs.netplay.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val dataSource: DataSource) : ViewModel() {

    private var _movies = MutableLiveData<Resource<List<MovieEntity>>>()
    val movie: LiveData<Resource<List<MovieEntity>>> = _movies
    private var _tvShow = MutableLiveData<Resource<List<TvShowEntity>>>()
    val tvShow: LiveData<Resource<List<TvShowEntity>>> = _tvShow


    fun loadMovie() {
        _movies.postValue(Resource.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _movies.postValue(Resource.Success(dataSource.fetchMovies()))
            } catch (t: Throwable) {
                _movies.postValue(Resource.Failure(t))
            }
        }
    }

    fun loadTvShow() {
        _tvShow.postValue(Resource.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _tvShow.postValue(Resource.Success(dataSource.fetchTvShow()))
            } catch (t: Throwable) {
                _tvShow.postValue(Resource.Failure(t))
            }
        }
    }
}