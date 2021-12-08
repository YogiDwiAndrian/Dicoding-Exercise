package com.kajangdev.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kajangdev.core.R
import com.kajangdev.core.databinding.ItemSliderBinding
import com.kajangdev.core.domain.model.Slides
import com.smarteist.autoimageslider.SliderViewAdapter
import java.util.*

class SliderAdapter : SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {

    private var listSlides = ArrayList<Slides>()

    fun setSlides(slides: List<Slides>?) {
        if (slides == null) return
        listSlides.addAll(slides)
        notifyDataSetChanged()
    }

    override fun getCount(): Int = listSlides.size

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val view = ItemSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderAdapterVH(view)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val slide = listSlides[position]
        viewHolder.bind(slide)
    }


    class SliderAdapterVH(private val binding: ItemSliderBinding) : SliderViewAdapter.ViewHolder(binding.root) {
        fun bind(slide: Slides) {
            with(binding) {
                Glide.with(itemView.context)
                        .load(slide.image)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.loading_poster)
                                        .error(R.drawable.error_poster)
                        )
                        .into(ivImageSlider)
            }
        }
    }
}