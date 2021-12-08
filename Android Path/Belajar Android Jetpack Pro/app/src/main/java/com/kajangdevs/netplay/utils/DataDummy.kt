package com.kajangdevs.netplay.utils

import com.kajangdevs.netplay.R
import com.kajangdevs.netplay.data.source.local.entity.HastagEntitiy
import com.kajangdevs.netplay.data.source.local.entity.SlideEntity

object DataDummy {

    fun generateDummySlides(): List<SlideEntity> {

        val slide = ArrayList<SlideEntity>()

        slide.add(SlideEntity(R.drawable.got))
        slide.add(SlideEntity(R.drawable.naruto))
        slide.add(SlideEntity(R.drawable.infinity))
        slide.add(SlideEntity(R.drawable.alita))
        slide.add(SlideEntity(R.drawable.aquaman))
        slide.add(SlideEntity(R.drawable.how))
        slide.add(SlideEntity(R.drawable.walking))

        return slide
    }

    fun generateDummyHastag(): List<HastagEntitiy> {

        val hastag = ArrayList<HastagEntitiy>()

        hastag.add(
            HastagEntitiy(
                R.drawable.tag_a,
                "#Dicoding"
            )
        )

        hastag.add(
            HastagEntitiy(
                R.drawable.tag_b,
                "#Awards"
            )
        )

        hastag.add(
            HastagEntitiy(
                R.drawable.tag_c,
                "#Cinematic"
            )
        )

        hastag.add(
            HastagEntitiy(
                R.drawable.tag_d,
                "#Booming"
            )
        )

        hastag.add(
            HastagEntitiy(
                R.drawable.tag_e,
                "#Story"
            )
        )

        return hastag
    }
}
