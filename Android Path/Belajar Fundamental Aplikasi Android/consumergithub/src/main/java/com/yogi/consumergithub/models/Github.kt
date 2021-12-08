package com.yogi.consumergithub.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Github(
    var id: Int = 0,
    var avatarFV: ByteArray? = null,
    var username: String = "",
    var url_html: String = ""
) : Parcelable