package com.charlesmuchene.kotlin.learn.views.recyclerview

import android.graphics.drawable.PictureDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.charlesmuchene.kotlin.learn.R
import com.charlesmuchene.kotlin.learn.models.Country
import com.charlesmuchene.kotlin.learn.utilities.OnItemClickListener
import com.charlesmuchene.kotlin.learn.utilities.orDash
import com.charlesmuchene.kotlin.learn.utilities.svg.SvgSoftwareLayerSetter

/**
 * Country view holder
 *
 * @param itemView [View] layout of item
 */
class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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
     * @param listener Nullable [OnItemClickListener] instance
     */
    fun bind(country: Country, listener: OnItemClickListener<Country>?) {

        val imageView = itemView.findViewById<ImageView>(R.id.flagImageView)
        requestBuilder
            .load(country.flag)
            .into(imageView)

        with(itemView) {
            with(country) {
                findViewById<TextView>(R.id.nameTextView).text = name
                findViewById<TextView>(R.id.capitalTextView).text = capital.orDash
                findViewById<TextView>(R.id.regionTextView).text =
                    arrayOf(region, subregion).filterNot { it.isEmpty() }.joinToString()
            }
        }

        itemView.setOnClickListener { listener?.invoke(country) }

    }
}