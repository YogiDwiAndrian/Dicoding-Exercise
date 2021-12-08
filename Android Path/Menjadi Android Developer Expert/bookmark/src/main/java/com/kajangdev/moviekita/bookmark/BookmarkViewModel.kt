package com.kajangdev.moviekita.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kajangdev.core.domain.usecase.UseCase

class BookmarkViewModel(useCase: UseCase) : ViewModel() {
    val getBookmarkMovie = useCase.getBookmarkMovie().asLiveData()
    val getBookmarkTV = useCase.getBookmarkTv().asLiveData()
}