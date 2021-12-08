package com.yogi.githubmedia.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Github(
    var id: Int = 0,
    var username: String = "",
    var name: String = "",
    var url_html: String = "",
    var avatar: String = "",
    var avatarFV: ByteArray? = null,
    var company: String = "",
    var location: String = "",
    var repository: Int = 0,
    var follower: Int = 0,
    var following: Int = 0
) : Parcelable