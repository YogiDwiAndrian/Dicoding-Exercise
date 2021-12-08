package com.kajangdev.moviekita.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kajangdev.core.domain.model.Movie
import com.kajangdev.core.domain.usecase.UseCase


class DetailViewModel(private val useCase: UseCase) : ViewModel() {
    fun checkBookmark(id: Int) =
            useCase.checkBookmark(id).asLiveData()

    fun insertItem(movie: Movie) = useCase.insertItem(movie)
    fun deleteItem(id: Int) = useCase.deleteItem(id)
}