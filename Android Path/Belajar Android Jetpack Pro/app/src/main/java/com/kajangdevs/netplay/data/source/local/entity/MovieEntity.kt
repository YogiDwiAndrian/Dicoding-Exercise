package com.kajangdevs.netplay.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    val movieId: Int,
    val title: String,
    val rating: Float,
    val released: String,
    val poster: String,
    val genreId: List<Int>
) : Parcelable