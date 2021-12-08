package com.kajangdevs.netplay.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ln
import kotlin.math.pow

fun String.asPosterUrl() =
    if (this.isEmpty()) Constant.DEFAULT_POSTER else Constant.IMG_BASE_URL + "w500" + this

fun String.asBackdropUrl() =
    if (this.isEmpty()) Constant.DEFAULT_BACKDROP else Constant.IMG_BASE_URL + "w780" + this

fun String.toYearDateFormat(): String {
    if (this.isEmpty() || this.isBlank()) return ""
    val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
    return date?.let {
        SimpleDateFormat("yyyy", Locale.getDefault()).format(it)
    } ?: ""
}

fun Long.withSuffix(): String {
    if (this < 1000) return "" + this
    val exp = (ln(this.toDouble()) / ln(1000.0)).toInt()
    return String.format(
        "%.1f %c",
        this / 1000.0.pow(exp.toDouble()),
        "KMB"[exp - 1]
    )
}

fun String.rmBracket() =
    this.replace("[", "").replace("]", "")

fun String.checkNull() =
    if (this.isEmpty()) "Unknown" else this

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

