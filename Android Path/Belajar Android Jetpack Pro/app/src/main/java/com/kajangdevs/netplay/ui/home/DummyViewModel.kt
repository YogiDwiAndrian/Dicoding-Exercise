package com.kajangdevs.netplay.ui.home

import androidx.lifecycle.ViewModel
import com.kajangdevs.netplay.data.source.local.entity.HastagEntitiy
import com.kajangdevs.netplay.data.source.local.entity.SlideEntity
import com.kajangdevs.netplay.utils.DataDummy

class DummyViewModel : ViewModel() {
    fun getHastag(): List<HastagEntitiy> = DataDummy.generateDummyHastag()
    fun getSlide(): List<SlideEntity> = DataDummy.generateDummySlides()
}