package com.kajangdev.moviekita.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kajangdev.core.domain.usecase.UseCase


class HomeViewModel(useCase: UseCase) : ViewModel() {

    val movie = useCase.getMovie().asLiveData()
    val tv = useCase.getTv().asLiveData()
    val slide = useCase.getSlides().asLiveData()
}