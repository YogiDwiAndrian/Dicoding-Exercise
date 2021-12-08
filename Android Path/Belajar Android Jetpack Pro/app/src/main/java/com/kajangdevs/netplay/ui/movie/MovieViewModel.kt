package com.kajangdevs.netplay.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kajangdevs.netplay.data.DataSource
import com.kajangdevs.netplay.data.source.local.entity.MovieEntity
import com.kajangdevs.netplay.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val dataSource: DataSource) : ViewModel() {

    private var _movies = MutableLiveData<Resource<List<MovieEntity>>>()
    val movie: LiveData<Resource<List<MovieEntity>>> = _movies

    fun load() {
        _movies.postValue(Resource.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _movies.postValue(Resource.Success(dataSource.fetchMovies()))
            } catch (t: Throwable) {
                _movies.postValue(Resource.Failure(t))
            }
        }
    }

    fun loadSearch(query: String) {
        _movies.postValue(Resource.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _movies.postValue(Resource.Success(dataSource.fetchMoviesSearch(query)))
            } catch (t: Throwable) {
                _movies.postValue(Resource.Failure(t))
            }
        }
    }
}