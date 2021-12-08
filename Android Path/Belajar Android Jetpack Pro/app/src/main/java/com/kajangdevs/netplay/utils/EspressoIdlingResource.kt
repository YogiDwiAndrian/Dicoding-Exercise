package com.kajangdevs.netplay.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "IDLING"
    private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    fun getEspressoIdlingResource(): IdlingResource = espressoTestIdlingResource
}