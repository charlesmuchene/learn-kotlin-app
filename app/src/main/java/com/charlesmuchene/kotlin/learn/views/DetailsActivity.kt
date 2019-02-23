package com.charlesmuchene.kotlin.learn.views

import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.charlesmuchene.kotlin.learn.R
import com.charlesmuchene.kotlin.learn.models.Country
import com.charlesmuchene.kotlin.learn.utilities.COUNTRY_PARCEL
import com.charlesmuchene.kotlin.learn.utilities.orDash
import com.charlesmuchene.kotlin.learn.utilities.show
import com.charlesmuchene.kotlin.learn.utilities.svg.SvgSoftwareLayerSetter
import kotlinx.android.synthetic.main.activity_details.*
import timber.log.Timber

/**
 * Country details activity
 */
class DetailsActivity : AppCompatActivity() {

    private val requestBuilder: RequestBuilder<PictureDrawable>
        get() = Glide.with(this)
            .`as`(PictureDrawable::class.java)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .listener(SvgSoftwareLayerSetter())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val country = intent.getParcelableExtra<Parcelable>(COUNTRY_PARCEL) as? Country
        country?.let(::displayCountry) ?: kotlin.run { errorView.show() }
        country?.name?.let(::setTitle)
    }

    /**
     * Display the given country details
     *
     * @param country [Country] instance
     */
    private fun displayCountry(country: Country) {
        with(country) {
            nameTextView.text = name
            capitalTextView.text = capital.orDash
            detailsTextView.text = details
            regionTextView.text = arrayOf(region, subregion).filterNot { it.isEmpty() }.joinToString()
            requestBuilder.load(flag).into(flagImageView)
        }
    }
}