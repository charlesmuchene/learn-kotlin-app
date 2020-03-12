package com.charlesmuchene.kotlin.learn.views.recyclerview

import android.graphics.drawable.PictureDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.charlesmuchene.kotlin.learn.R
import com.charlesmuchene.kotlin.learn.databinding.CountryItemLayoutBinding
import com.charlesmuchene.kotlin.learn.models.Country
import com.charlesmuchene.kotlin.learn.utilities.OnItemClickListener
import com.charlesmuchene.kotlin.learn.utilities.svg.SvgSoftwareLayerSetter

/**
 * Country view holder
 *
 * @param binding [CountryItemLayoutBinding] instance
 */
class CountryViewHolder(private val binding: CountryItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val requestBuilder: RequestBuilder<PictureDrawable>
        get() = Glide.with(itemView.context)
            .`as`(PictureDrawable::class.java)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .listener(SvgSoftwareLayerSetter())

    /**
     * Bind the given country to the view
     *
     * @param country [Country] to bind
     * @param listener [OnItemClickListener] instance
     */
    fun bind(country: Country, listener: OnItemClickListener<Country>) {
        binding.country = country
        binding.executePendingBindings()

        requestBuilder
            .load(country.flag)
            .into(binding.flagImageView)

        binding.root.setOnClickListener { listener.invoke(country) }

    }
}