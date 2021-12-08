package com.kajangdevs.netplay.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kajangdevs.netplay.R
import com.kajangdevs.netplay.data.source.local.entity.SlideEntity
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.item_slider.view.*
import java.util.*

class SliderAdapter : SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {

    private var listSlides = ArrayList<SlideEntity>()

    fun setSlides(slides: List<SlideEntity>?) {
        if (slides == null) return
        this.listSlides.clear()
        this.listSlides.addAll(slides)
    }

    override fun getCount(): Int = listSlides.size

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_slider, parent, false)
        return SliderAdapterVH(view)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val slide = listSlides[position]
        viewHolder.bind(slide)
    }


    class SliderAdapterVH(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        fun bind(slide: SlideEntity) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(slide.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(iv_auto_image_slider)
            }
        }
    }
}