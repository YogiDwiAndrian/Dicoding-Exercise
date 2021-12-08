package com.kajangdev.core.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun asPosterUrl(url: String) =
        if (url.isEmpty()) Constant.DEFAULT_POSTER else Constant.IMG_BASE_URL + "w500" + url

fun asBackdropUrl(url: String) =
        if (url.isEmpty()) Constant.DEFAULT_BACKDROP else Constant.IMG_BASE_URL + "w780" + url

fun toYearDateFormat(date: String): String {
    if (date.isEmpty() || date.isBlank()) return ""
    val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date)
    return formatDate?.let {
        SimpleDateFormat("yyyy", Locale.getDefault()).format(it)
    } ?: ""
}

fun String.checkNull() =
        if (this.isEmpty()) "Unknown" else this

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}