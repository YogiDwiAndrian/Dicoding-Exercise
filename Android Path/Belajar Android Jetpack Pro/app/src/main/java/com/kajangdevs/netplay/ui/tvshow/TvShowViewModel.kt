package com.kajangdevs.netplay.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kajangdevs.netplay.data.DataSource
import com.kajangdevs.netplay.data.source.local.entity.TvShowEntity
import com.kajangdevs.netplay.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowViewModel(private val dataSource: DataSource) : ViewModel() {

    private var _tvShow = MutableLiveData<Resource<List<TvShowEntity>>>()
    val tvShow: LiveData<Resource<List<TvShowEntity>>> = _tvShow

    fun load() {
        _tvShow.postValue(Resource.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _tvShow.postValue(Resource.Success(dataSource.fetchTvShow()))
            } catch (t: Throwable) {
                _tvShow.postValue(Resource.Failure(t))
            }
        }
    }

    fun loadSearch(query: String) {
        _tvShow.postValue(Resource.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _tvShow.postValue(Resource.Success(dataSource.fetchTvShowSearch(query)))
            } catch (t: Throwable) {
                _tvShow.postValue(Resource.Failure(t))
            }
        }
    }
}