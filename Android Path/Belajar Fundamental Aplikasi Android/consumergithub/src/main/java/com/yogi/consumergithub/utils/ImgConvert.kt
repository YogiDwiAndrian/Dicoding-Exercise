package com.yogi.githubmedia.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import java.io.ByteArrayOutputStream

object ImgConvert {
    fun imageToBitmap(image: ImageView): ByteArray {
        val bitmap = (image.drawable as? BitmapDrawable)?.bitmap
        val stream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 0, stream)

        return stream.toByteArray()
    }
    fun bitmapToImage(image: ByteArray): Bitmap?{
        return BitmapFactory.decodeByteArray(image, 0, image.size)
    }
}