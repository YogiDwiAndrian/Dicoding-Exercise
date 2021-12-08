package com.kajangdev.moviekita.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kajangdev.core.domain.usecase.UseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class MovieViewModel(useCase: UseCase) : ViewModel() {
    val movie = useCase.getMovie().asLiveData()

    private val queryChannel = ConflatedBroadcastChannel<String>()

    fun setSearchQuery(search: String) {
        queryChannel.offer(search)
    }

    val movieResult = queryChannel.asFlow()
            .debounce(1000)
            .distinctUntilChanged()
            .filter {
                it.trim().isNotEmpty()
            }
            .flatMapLatest {
                useCase.getMovieSearch(it)
            }.asLiveData()
}