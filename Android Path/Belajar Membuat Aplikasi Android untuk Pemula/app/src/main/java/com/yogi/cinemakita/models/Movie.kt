package com.yogi.cinemakita.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    var tittle: String = "",
    var genre: String = "",
    var synopsis: String = "",
    var preview: String = "",
    var release: Int = 0,
    var rating: Float = 0f,
    var poster: Int = 0
) : Parcelable