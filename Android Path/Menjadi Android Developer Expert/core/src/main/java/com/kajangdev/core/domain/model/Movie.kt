package com.kajangdev.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
        val id: Int?,
        val title: String?,
        val overview: String?,
        val rating: Float?,
        val voteCount: String?,
        val released: String?,
        val poster: String?,
        val backdrop: String?,
        val genre: String?,
        val language: String?,
        val isTvShow: Boolean?,
) : Parcelable